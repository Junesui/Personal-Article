package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.dto.BlogQueryDTO;
import com.blog.dto.BlogTypeTagDTO;
import com.blog.entity.Blog;

/**
 * 博客mapper接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Mapper
public interface BlogMapper {

	List<BlogTypeTagDTO> listBlogAndType();

	Long save(BlogTypeTagDTO dto);

	Blog findById(Long id);

	void update(Blog blog);

	List<BlogTypeTagDTO> listBySearch(BlogQueryDTO blogQueryDTO);

	BlogTypeTagDTO findBlogAndTypeById(Long id);

	void deleteById(Long id);

	void deleteByTypeId(Long typeId);

	List<BlogTypeTagDTO> listTopBlog();

	List<Blog> listTopRecommendBlog(Integer topRecommendNum);

	BlogTypeTagDTO findBlogDetailById(Long id);

}
