package com.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.article.dto.ArticleQueryDTO;
import com.article.dto.ArticleTypeTagDTO;
import com.article.entity.Article;

/**
 * 文章mapper接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Mapper
public interface ArticleMapper {

	List<ArticleTypeTagDTO> listArticleAndType();

	Long save(ArticleTypeTagDTO dto);

	Article findById(Long id);

	void update(Article article);

	List<ArticleTypeTagDTO> listBySearch(ArticleQueryDTO articleQueryDTO);

	ArticleTypeTagDTO findArticleAndTypeById(Long id);

	void deleteById(Long id);

	void deleteByTypeId(Long typeId);

	List<ArticleTypeTagDTO> listShowArticle();

	ArticleTypeTagDTO findArticleDetailById(Long id);

	void incViewCntById(Long viewCntWrite,Long id);

	List<ArticleTypeTagDTO> listTypeArticleByTypeId(Long id);

	List<ArticleTypeTagDTO> listTagArticleByTagId(Long id);

	List<Article> archiveArticle();

	List<ArticleTypeTagDTO> listByQuery(String query);

	void incCommentCntById(Long articleId);

	Integer countShow();

	List<Article> listTopRecommendArticle();

	Article findArticleLikeCntById(Long articleId);

	void updateLikeCnt(Article article);

}
