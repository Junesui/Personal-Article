package com.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;

/**
 * 博客评论实现类
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public void deleteByBlogId(Long blogId) {

		commentMapper.deleteByBlogId(blogId);
	}

}
