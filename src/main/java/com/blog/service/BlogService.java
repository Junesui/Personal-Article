package com.blog.service;

import java.util.List;
import java.util.Map;

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

	//列出首页展示的博客相关信息
	public List<BlogTypeTagDTO> listTopBlog();

	//列出首页展示的推荐博客
	public List<Blog> listTopRecommendBlog(Integer topRecommendNum);

	//获取博客并转换博客内容的格式
	public BlogTypeTagDTO findAndConvertById(Long id);

	//根据id增加博客访问数量
	public void incViewCntById(Long id);

	//通过分类id获取博客相关信息
	public List<BlogTypeTagDTO> listTypeBlogByTypeId(Long id);

	//通过标签id获取博客相关信息
	public List<BlogTypeTagDTO> listTagBlogByTagId(Long id);

	//统计博客的总数
	public Long count();

	//博客归档
	public List<Blog> archiveBlog();

	//通过关键字搜索博客
	public List<BlogTypeTagDTO> listByQuery(String query);

}
