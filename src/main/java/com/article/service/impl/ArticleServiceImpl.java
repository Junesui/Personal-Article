package com.article.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.article.dto.ArticleQueryDTO;
import com.article.dto.ArticleTypeTagDTO;
import com.article.entity.Article;
import com.article.exception.CustomizeErrorCode;
import com.article.exception.CustomizeException;
import com.article.mapper.ArticleMapper;
import com.article.mapper.TagMapper;
import com.article.service.ArticleService;
import com.article.service.CommentService;
import com.article.util.ElasticSearchOptUtils;
import com.article.util.MarkdownUtils;
import com.article.util.RedisUtil;
import com.article.util.StringAndListConvertUtils;

/**
 * 文章service实现类
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private CommentService commentService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ElasticSearchOptUtils esOptUtils;
	 

	//存放文章访问的数次数
	private Long viewCnt = 0L;
	//文章访问数量达到viewCntWrite次，再一次性写入数据库
	@Value("${article.viewCntWrite}")
	private Long viewCntWrite;
	//redis中存文章点赞数的key名
	@Value("${article.likeCount.key}")
	private String articleLikeCountKey;
	//redis中存文章点赞数的field名 ["articleId-" + 文章id]
	@Value("${article.likeCount.field}")
	private String articleLikeCountField;
	

	@Override
	public List<ArticleTypeTagDTO> listArticleAndType() {
		return articleMapper.listArticleAndType();
	}

	@Transactional
	@Override
	public void saveOrUpdate(ArticleTypeTagDTO dto) {

		//字符串标签ids转换为列表
		List<Long> tagIdList = StringAndListConvertUtils.toList(dto.getTagIds());

		Long articleId = dto.getId();
		if (articleId == null) {
			//插入操作
			dto.setCreateTime(new Date(System.currentTimeMillis()));
			dto.setUpdateTime(new Date(System.currentTimeMillis()));
			dto.setViewCount(0);
			dto.setCommentCount(0);
			
			//往mysql中插入文章
			articleMapper.save(dto);
			//插入article_tag中间表
			for (Long tagId : tagIdList) {
				tagMapper.saveArticleAndTag(dto.getId(), tagId);
			}
			
			Long id = dto.getId();
			//初始化redis缓存数据库中的点赞数
			redisUtil.hPut("article", "likeCount" + id, Integer.toString(0));
			
			//往ElasticSearch中插入查询时候搜索的字段内容【插入标题和描述】
			esOptUtils.insert(dto);
		} else {
			//更新操作
			Article b = articleMapper.findById(articleId);
			BeanUtils.copyProperties(dto, b);
			b.setUpdateTime(new Date(System.currentTimeMillis()));
			//更新文章
			articleMapper.update(b);
			//删除该文章的article_tag中间表数据，重新插入article_tag中间表
			tagMapper.deleteByArticleId(articleId);
			for (Long tagId : tagIdList) {
				tagMapper.saveArticleAndTag(articleId, tagId);
			}
			
			//根据id更新lasticSearch中的内容【更新标题和描述】
			esOptUtils.update(dto, articleId);
		}
	}

	@Override
	public List<ArticleTypeTagDTO> listBySearch(ArticleQueryDTO articleQueryDTO) {

		return articleMapper.listBySearch(articleQueryDTO);
	}

	@Override
	public ArticleTypeTagDTO findArticleAndTypeById(Long id) {
		return articleMapper.findArticleAndTypeById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {

		//删除中间表
		tagMapper.deleteByArticleId(id);
		//删除评论
		commentService.deleteByArticleId(id);
		//删除文章
		articleMapper.deleteById(id);

	}

	@Override
	public List<ArticleTypeTagDTO> listShowArticle() {
		List<ArticleTypeTagDTO> articles = articleMapper.listShowArticle();
		for (ArticleTypeTagDTO article : articles) {
			Object likeCount = redisUtil.hGet(articleLikeCountKey, articleLikeCountField + article.getId());
			article.setLikeCount((Integer) likeCount);
		}
		return articles;
	}

	@Override
	public ArticleTypeTagDTO findAndConvertById(Long id) {
		ArticleTypeTagDTO articleTypeTagDTO = articleMapper.findArticleDetailById(id);
		if (articleTypeTagDTO == null) {
			throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND);
		}
		String content = articleTypeTagDTO.getContent();
		articleTypeTagDTO.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
		return articleTypeTagDTO;
	}

	@Override
	public void incViewCntById(Long id) {
		viewCnt = viewCnt + 1;
		//访问数量增加7次后，再一次性写入数据库
		if (viewCnt == viewCntWrite || viewCnt > viewCntWrite) {
			viewCnt = 0L;
			articleMapper.incViewCntById(viewCntWrite, id);
		}
	}

	@Override
	public List<ArticleTypeTagDTO> listTypeArticleByTypeId(Long id) {
		return articleMapper.listTypeArticleByTypeId(id);
	}

	@Override
	public List<ArticleTypeTagDTO> listTagArticleByTagId(Long id) {
		return articleMapper.listTagArticleByTagId(id);
	}

	@Override
	public List<Article> archiveArticle() {
		return articleMapper.archiveArticle();
	}

	@Override
	public List<ArticleTypeTagDTO> listByQuery(String query) {
		return articleMapper.listByQuery(query);
	}

	@Override
	public List<Article> listTopRecommendArticle() {
		return articleMapper.listTopRecommendArticle();
	}

	@Override
	public void incLikeCntByArticleId(Long articleId) {
		redisUtil.hIncrBy(articleLikeCountKey, articleLikeCountField + articleId, 1);
	}
}
