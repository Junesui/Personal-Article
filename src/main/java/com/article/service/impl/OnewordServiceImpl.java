package com.article.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.article.entity.Oneword;
import com.article.mapper.OnewordMapper;
import com.article.service.OnewordService;

/**
 * 每日一句实现类
 * @author June
 * @date 2020/05/31
 * @version V1.0
 */
@Service
public class OnewordServiceImpl implements OnewordService{

	@Autowired
	private OnewordMapper onewordMapper;
	
	
	@Override
	public List<Oneword> listBysize(Integer size) {
		return onewordMapper.listBysize(size);
	}

	@Override
	public List<Oneword> list() {
		return onewordMapper.list();
	}

	@Override
	public void saveOrUpdate(Oneword oneword) {
		
		Long id = oneword.getId();
		if (id == null) {
			//保存
			oneword.setCreateTime(new Date(System.currentTimeMillis()));
			oneword.setUpdateTime(new Date(System.currentTimeMillis()));
			onewordMapper.save(oneword);
		}else {
			//更新
			oneword.setUpdateTime(new Date(System.currentTimeMillis()));
			onewordMapper.update(oneword);
		}
	}

	@Override
	public Oneword findById(Long id) {
		return onewordMapper.findById(id);
	}

	@Override
	public void updateDel(Long id) {
		onewordMapper.updateDel(id);
	}

}
