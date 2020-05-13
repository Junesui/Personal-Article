package com.blog.service;

import java.util.List;

import com.blog.dto.BlogQueryDTO;
import com.blog.dto.BlogTypeTagDTO;
import com.blog.entity.Blog;

/**
 * 博客service接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public interface BlogService {

	//列出所有博客和对应的分类名称
	public List<BlogTypeTagDTO> listBlogAndTypeName();

	//保存或更新博客
	public int saveOrUpdate(BlogTypeTagDTO blogTypeTagDTO);

	//搜索博客
	public List<BlogTypeTagDTO> listBySearch(BlogQueryDTO blogQueryDTO);

}
