package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.entity.Tag;

/**
 * 标签mapper接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Mapper
public interface TagMapper {

	List<Tag> list();

	void saveBlogAndTag(Long blogId, Long tagId);

	void deleteByBlogId(Long blogId);

	Tag findByName(String name);

	void save(Tag tag);

	List<Long> listByBlogId(Long blogId);

	Tag findById(Long id);

	void update(Tag tag);

	void deleteByTagId(Long id);

	void deleteById(Long id);

}
