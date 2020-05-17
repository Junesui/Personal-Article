package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.TagBlogNumDTO;
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
	public void save(Tag tag) {
		tag.setCreateTime(new Date(System.currentTimeMillis()));
		tag.setUpdateTime(new Date(System.currentTimeMillis()));
		tagMapper.save(tag);
	}

	@Override
	public List<Tag> listByBlogId(Long BlogId) {
		return tagMapper.listByBlogId(BlogId);
	}

	@Override
	public Tag findById(Long id) {
		return tagMapper.findById(id);
	}

	@Override
	public void update(Tag tag) {
		tag.setUpdateTime(new Date(System.currentTimeMillis()));
		tagMapper.update(tag);
	}

	@Override
	public void deleteById(Long id) {
		//删除中间表
		tagMapper.deleteByTagId(id);
		//删除标签
		tagMapper.deleteById(id);
	}

	@Override
	public List<TagBlogNumDTO> listTopTag(Integer topTagNum) {
		return tagMapper.listTopTag(topTagNum);
	}

	@Override
	public Long count() {
		return tagMapper.count();
	}

	@Override
	public List<TagBlogNumDTO> listTagShow() {
		return tagMapper.listTagShow();
	}

}
