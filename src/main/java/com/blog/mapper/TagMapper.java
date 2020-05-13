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

	int saveBlogAndTag(Long blogId, Long tagId);

	void deleteByBlogId(Long blogId);

	Tag findByName(String name);

	int save(Tag tag);

}
