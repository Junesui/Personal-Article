package com.blog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Oneword;
import com.blog.mapper.OnewordMapper;
import com.blog.service.OnewordService;

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
	public List<Oneword> list(Integer size) {
		return onewordMapper.list(size);
	}

}
