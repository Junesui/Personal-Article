package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Type;
import com.blog.mapper.TypeMapper;
import com.blog.service.TypeService;

/**
 * 分类service实现类
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Service
public class TypeServiceImpl implements TypeService {

	@Autowired
	private TypeMapper typeMapper;
	
	@Override
	public List<Type> list() {
		return typeMapper.list();
	}

	@Override
	public Type findByName(String name) {
		return typeMapper.findByName(name);
	}

	@Override
	public int save(Type type) {
		type.setCreateTime(new Date(System.currentTimeMillis()));
		type.setUpdateTime(new Date(System.currentTimeMillis()));
		return typeMapper.save(type);
	}

}
