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

	List<BlogTypeTagDTO> listBlogAndTypeName();

	int save(BlogTypeTagDTO dto);

	BlogTypeTagDTO findById(Long id);

	int update(Blog blog);

	List<BlogTypeTagDTO> listBySearch(BlogQueryDTO blogQueryDTO);

}
