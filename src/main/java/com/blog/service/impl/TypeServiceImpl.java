package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.TypeBlogNumDTO;
import com.blog.entity.Type;
import com.blog.mapper.BlogMapper;
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
	@Autowired
	private BlogMapper blogMapper;

	@Override
	public List<Type> list() {
		return typeMapper.list();
	}

	@Override
	public Type findByName(String name) {
		return typeMapper.findByName(name);
	}

	@Override
	public void save(Type type) {
		type.setCreateTime(new Date(System.currentTimeMillis()));
		type.setUpdateTime(new Date(System.currentTimeMillis()));
		typeMapper.save(type);
	}

	@Override
	public Type findById(Long id) {
		return typeMapper.findById(id);
	}

	@Override
	public void update(Type type) {
		type.setUpdateTime(new Date(System.currentTimeMillis()));
		typeMapper.update(type);
	}

	@Override
	public void deleteById(Long id) {
		//删除博客
		blogMapper.deleteByTypeId(id);
		//删除分类
		typeMapper.deleteById(id);

	}

	@Override
	public List<TypeBlogNumDTO> listTopType(Integer topTypeNum) {
		return typeMapper.listTopType(topTypeNum);
	}

}
