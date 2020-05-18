package com.blog.service;

import java.util.List;

import com.blog.dto.CommentExtDTO;
import com.blog.entity.Comment;

/**
 * 博客评论接口
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
public interface CommentService {

	//通过博客id删除评论
	public void deleteByBlogId(Long blogId);

	//保存评论
	public void save(Comment comment);

	//列出一级评论
	public List<CommentExtDTO> listParentCommentByBlogId(Long blogId);
}
