package com.blog.service;

import java.util.List;

import com.blog.dto.TagBlogNumDTO;
import com.blog.entity.Tag;

/**
 * 标签service接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public interface TagService {

	//列出所有的标签
	List<Tag> list();

	//通过名字找到标签
	Tag findByName(String name);

	//保存标签
	void save(Tag tag);

	//通过博客id查找对应的标签id
	List<Tag> listByBlogId(Long BlogId);

	//通过id查找标签
	Tag findById(Long id);

	//更新标签
	void update(Tag tag);

	//删除标签和中间表
	void deleteById(Long id);

	//列出博客首页的标签
	List<TagBlogNumDTO> listTopTag(Integer topTagNum);

	//统计有博客的标签总数
	Long count();

	//列出标签及对应的博客数量
	List<TagBlogNumDTO> listTagShow();

}
