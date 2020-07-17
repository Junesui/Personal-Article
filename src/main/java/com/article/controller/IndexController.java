package com.article.controller;

import java.util.List;

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
	
	//文章首页展示的文章数量
	@Value("${index.pageArticleSize}")
	private Integer pageArticleSize;
	//每日一句展示的数量
	@Value("${index.onewordSize}")
	private Integer onewordSize;
	
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
			              @RequestParam(name = "size", defaultValue = "10") Integer size, 
			              Model model) {
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
		}else {
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

		//增加文章访问次数
		articleService.incViewCntById(id);

		model.addAttribute("articleTypeTagDTO", articleTypeTagDTO);
		model.addAttribute("tags", tags);
		return "article";
	}

	/**
	 * 搜索文章
	 * @param page 页码
	 * @param size 每页文章的数量
	 * @param query 搜索关键字
	 * @param model
	 * @return 搜索结果页面
	 */
	@PostMapping("/search")
	public String search(@RequestParam(name = "page", defaultValue = "1") Integer page,
			             @RequestParam(name = "size", defaultValue = "10") Integer size, 
			             @RequestParam String query, Model model) {
		size = pageArticleSize;
		//分页
		PageHelper.startPage(page, size);
		List<ArticleTypeTagDTO> articleTypeTagDTOs = articleService.listByQuery(query);
		
		PageInfo<ArticleTypeTagDTO> pageInfo = new PageInfo<>(articleTypeTagDTOs);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("query", query);
		return "search";
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
	public Object feedback(@Validated Feedback feedback,BindingResult result,Model model) {
		//字段验证
		if (result.hasErrors()) {
			return "forward:/";
		}
		//保存反馈
		feedbackService.save(feedback);
		return new Feedback();
	}

}
