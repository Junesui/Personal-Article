package com.article.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.article.dto.TagArticleNumDTO;
import com.article.entity.Tag;
import com.article.mapper.TagMapper;
import com.article.service.TagService;

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
	public List<Tag> listByArticleId(Long ArticleId) {
		return tagMapper.listByArticleId(ArticleId);
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

	@Transactional
	@Override
	public void deleteById(Long id) {
		//删除中间表
		tagMapper.deleteByTagId(id);
		//删除标签
		tagMapper.deleteById(id);
	}

	@Override
	public Long count() {
		return tagMapper.count();
	}

	@Override
	public List<TagArticleNumDTO> listTagAndArticle() {
		return tagMapper.listTagAndArticle();
	}

}
