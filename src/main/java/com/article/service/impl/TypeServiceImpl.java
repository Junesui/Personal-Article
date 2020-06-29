package com.article.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.article.dto.TypeArticleNumDTO;
import com.article.entity.Type;
import com.article.mapper.ArticleMapper;
import com.article.mapper.TypeMapper;
import com.article.service.TypeService;

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
	private ArticleMapper articleMapper;

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

	@Transactional
	@Override
	public void deleteById(Long id) {
		//删除文章
		articleMapper.deleteByTypeId(id);
		//删除分类
		typeMapper.deleteById(id);

	}

	@Override
	public Long count() {
		return typeMapper.count();
	}

	@Override
	public List<TypeArticleNumDTO> listTypeAndArticle() {
		return typeMapper.listTypeAndArticle();
	}

}
