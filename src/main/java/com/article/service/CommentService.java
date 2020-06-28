package com.article.service;

import java.util.List;

import com.article.dto.CommentExtDTO;
import com.article.entity.Comment;

/**
 * 文章评论接口
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
public interface CommentService {

	//通过文章id删除评论
	public void deleteByArticleId(Long articleId);

	//保存评论
	public void save(Comment comment);

	//列出一级评论
	public List<CommentExtDTO> listParentCommentByArticleId(Long articleId);

	//通过评论id增加点赞的数量
	public void incLikeCntByCommentId(Long commentId);

	//通过评论id增加踩的数量
	public void incDownCntByCommentId(Long commentId);
}
