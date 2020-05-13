package com.blog.service;

import java.util.List;

import com.blog.entity.Type;

/**
 * 分类service接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public interface TypeService {

	//列出所有类型
	List<Type> list();

	//通过名字查找分类
	Type findByName(String name);

	//保存分类
	int save(Type type);

}
