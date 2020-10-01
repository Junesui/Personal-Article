package com.article.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.article.dto.ArticleTypeTagDTO;
import com.article.entity.Article;
import com.article.entity.Feedback;
import com.article.entity.Oneword;
import com.article.entity.Siteinfo;
import com.article.entity.Tag;
import com.article.entity.Tool;
import com.article.service.ArticleService;
import com.article.service.FeedbackService;
import com.article.service.OnewordService;
import com.article.service.SiteinfoService;
import com.article.service.TagService;
import com.article.service.ToolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 首页控制器
 * @author June
 * @date 2020/05/17
 * @version V1.0
 */
@Controller
public class IndexController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private TagService tagService;
	@Autowired
	private OnewordService onewordService;
	@Autowired
	private ToolService toolService;
	@Autowired
	private SiteinfoService siteinfoService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private RestHighLevelClient restHighLevelClient;

	//文章首页展示的文章数量
	@Value("${index.pageArticleSize}")
	private Integer pageArticleSize;
	//每日一句展示的数量
	@Value("${index.onewordSize}")
	private Integer onewordSize;
	//ElasticSearch中文章的索引名
	@Value("${es.indexName}")
	private String esIndexName;
	//ElasticSearch中存文章的标题名
	@Value("${es.articleTitle}")
	private String esArticleTitle;
	//ElasticSearch中存文章的描述
	@Value("${es.articleDesc}")
	private String esArticleDesc;

	//存放网站访问的次数
	private Long viewCnt = 0L;
	//网站访问数量达到viewCntWrite次，再一次性写入数据库
	@Value("${siteinfo.viewCntWrite}")
	private Long viewCntWrite;

	/**
	 * 跳转到文章首页
	 * @param page 页码
	 * @param size 每页文章的数量
	 * @param model
	 * @return 文章首页
	 */
	@GetMapping("/")
	public String toIndex(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, Model model) {
		size = pageArticleSize;
		//分页
		PageHelper.startPage(page, size);
		List<ArticleTypeTagDTO> articleTypeTagDTOs = articleService.listShowArticle();

		PageInfo<ArticleTypeTagDTO> pageInfo = new PageInfo<>(articleTypeTagDTOs);
		List<Oneword> onewords = onewordService.listBysize(onewordSize);
		List<Article> articles = articleService.listTopRecommendArticle();
		List<Tool> tools = toolService.list();

		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("onewords", onewords);
		model.addAttribute("articles", articles);
		model.addAttribute("tools", tools);

		// 写入siteinfo表
		Siteinfo siteinfo = siteinfoService.find();
		if (siteinfo == null) {
			siteinfoService.save();
		} else {
			viewCnt = viewCnt + 1;
			if (viewCnt == viewCntWrite || viewCnt > viewCntWrite) {
				viewCnt = 0L;
				siteinfoService.incViewCnt(viewCntWrite);
			}
		}

		return "index";
	}

	/**
	 * 跳转到文章详情页
	 * @param id 文章id
	 * @param model
	 * @return 文章详情页
	 */
	@GetMapping("/article/{id}")
	public String toArticle(@PathVariable("id") Long id, Model model) {
		ArticleTypeTagDTO articleTypeTagDTO = articleService.findAndConvertById(id);
		List<Tag> tags = tagService.listByArticleId(id);
		List<Tool> tools = toolService.list();

		//增加文章访问次数
		articleService.incViewCntById(id);

		model.addAttribute("articleTypeTagDTO", articleTypeTagDTO);
		model.addAttribute("tags", tags);
		model.addAttribute("tools", tools);
		return "article";
	}

	/**
	 * 搜索文章
	 * @param query 搜索关键字
	 * @param model
	 * @return 搜索结果页面
	 */
	@PostMapping("/search")
	public String search(@RequestParam(name = "query") String query, Model model) {

		// 查询请求
		SearchRequest searchRequest = new SearchRequest(esIndexName);

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		//组合查询
		MultiMatchQueryBuilder multiMatchQuery = QueryBuilders.multiMatchQuery(query, esArticleTitle, esArticleDesc);
		
		sourceBuilder.query(multiMatchQuery);
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		//高亮
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		//设置高亮字段
		highlightBuilder.field(query);
		//关闭多个高亮显示
		highlightBuilder.requireFieldMatch(false);
		//设置标签头
		highlightBuilder.preTags("<span style='color:red'>");
		//设置标签尾
		highlightBuilder.postTags("</span>");
		//设置需要高亮的字段。 不然无法高亮
		highlightBuilder.field(esArticleTitle).field(esArticleDesc);
		
		sourceBuilder.highlighter(highlightBuilder);
		
		searchRequest.source(sourceBuilder);
		try {
			SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			//解析结果
			for (SearchHit documentFields : search.getHits().getHits()) {
				Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
				HighlightField articleTitle = highlightFields.get(esArticleTitle);
				HighlightField articleDesc = highlightFields.get(esArticleDesc);
				Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();

				//解析高亮的字段，将原来的字段换为高亮字段即可
				//标题
				if (articleTitle != null) {
					Text[] fragments = articleTitle.fragments();

					String new_title = "";

					for (Text text : fragments) {
						new_title += text;
					}
					sourceAsMap.put(esArticleTitle, new_title);
				}
				//描述
				if (articleDesc != null) {
					Text[] fragments = articleDesc.fragments();
					
					String new_desc = "";
					for (Text text : fragments) {
						new_desc += text;
					}
					sourceAsMap.put(esArticleDesc, new_desc);
				}
				list.add(sourceAsMap);
			}
			
			//查询到的文章数量
			Long total = search.getHits().getTotalHits().value;

			List<Tool> tools = toolService.list();

			model.addAttribute("total", total);
			model.addAttribute("esList", list);
			model.addAttribute("query", query);
			model.addAttribute("tools", tools);
			return "search";

		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 * 保存反馈建议
	 * @param feedback 反馈建议
	 * @param result
	 * @param model
	 * @return 首页
	 */
	@ResponseBody
	@PostMapping("/feedback")
	public Object feedback(@Validated Feedback feedback, BindingResult result, Model model) {
		//字段验证
		if (result.hasErrors()) {
			return "forward:/";
		}
		//保存反馈
		feedbackService.save(feedback);
		return new Feedback();
	}

	/**
	 * 根据文章id增加赞的数量
	 * @param articleId 文章id
	 * @return 文章的评论列表片段
	 */
	@ResponseBody
	@GetMapping("/incArticleLikeCnt")
	public Object incLikeCntByArticleId(Long articleId) {
		articleService.incLikeCntByArticleId(articleId);
		return null;
	}

}
