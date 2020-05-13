package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Tag;
import com.blog.mapper.TagMapper;
import com.blog.service.TagService;

/**
 * 标签service实现类
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagMapper tagMapper;
	
	@Override
	public List<Tag> list() {
		return tagMapper.list();
	}

	@Override
	public Tag findByName(String name) {
		return tagMapper.findByName(name);
	}

	@Override
	public int save(Tag tag) {
		tag.setCreateTime(new Date(System.currentTimeMillis()));
		tag.setUpdateTime(new Date(System.currentTimeMillis()));
		return tagMapper.save(tag);
	}

}
