package com.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 博客评论接口
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
@Mapper
public interface CommentMapper {

	public void deleteByBlogId(Long blogId);

	
}
