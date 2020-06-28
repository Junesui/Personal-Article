package com.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.article.dto.CommentExtDTO;
import com.article.entity.Comment;

/**
 * 文章评论接口
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
@Mapper
public interface CommentMapper {

	void deleteByArticleId(Long articleId);

	void save(Comment comment);

	List<CommentExtDTO> listParentCommentByArticleId(Long articleId);

	List<CommentExtDTO> listChildCommentByParentId(Long parentId);

	void incReplyCntById(Long id);

	void incLikeCntByCommentId(Long commentId);

	void incDownCntByCommentId(Long commentId);

	Integer count();
}
