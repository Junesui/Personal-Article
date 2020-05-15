package com.blog.service;

/**
 * 博客评论接口
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
public interface CommentService {

	//通过博客id删除评论
	public void deleteByBlogId(Long blogId);
}
