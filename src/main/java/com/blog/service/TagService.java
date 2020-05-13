package com.blog.service;

import java.util.List;

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

	int save(Tag tag);

}
