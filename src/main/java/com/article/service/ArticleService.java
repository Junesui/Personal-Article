package com.article.service;

import java.util.List;

import com.article.dto.ArticleQueryDTO;
import com.article.dto.ArticleTypeTagDTO;
import com.article.entity.Article;

/**
 * 文章service接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public interface ArticleService {

	//通过id查找文章和对应的类型，标签
	public ArticleTypeTagDTO findArticleAndTypeById(Long id);
	
	//列出所有文章和对应的分类名称
	public List<ArticleTypeTagDTO> listArticleAndType();

	//保存或更新文章
	public void saveOrUpdate(ArticleTypeTagDTO articleTypeTagDTO);

	//搜索文章
	public List<ArticleTypeTagDTO> listBySearch(ArticleQueryDTO articleQueryDTO);

	//通过id删除文章，中间表，评论
	public void deleteById(Long id);

	//列出首页展示的文章相关信息
	public List<ArticleTypeTagDTO> listShowArticle();

	//获取文章并转换文章内容的格式
	public ArticleTypeTagDTO findAndConvertById(Long id);

	//根据id增加文章访问数量
	public void incViewCntById(Long id);

	//通过分类id获取文章相关信息
	public List<ArticleTypeTagDTO> listTypeArticleByTypeId(Long id);

	//通过标签id获取文章相关信息
	public List<ArticleTypeTagDTO> listTagArticleByTagId(Long id);

	//文章归档
	public List<Article> archiveArticle();

	//通过关键字搜索文章
	public List<ArticleTypeTagDTO> listByQuery(String query);

	//列出首页展示的推荐文章
	public List<Article> listTopRecommendArticle();

}
