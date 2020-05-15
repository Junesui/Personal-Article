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

	//通过id查找博客和对应的类型，标签
	public BlogTypeTagDTO findBlogAndTypeById(Long id);
	
	//列出所有博客和对应的分类名称
	public List<BlogTypeTagDTO> listBlogAndType();

	//保存或更新博客
	public void saveOrUpdate(BlogTypeTagDTO blogTypeTagDTO);

	//搜索博客
	public List<BlogTypeTagDTO> listBySearch(BlogQueryDTO blogQueryDTO);

	//通过id删除博客，中间表，评论
	public void deleteById(Long id);

}
