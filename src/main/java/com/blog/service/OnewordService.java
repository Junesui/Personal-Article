package com.blog.service;

import java.util.List;

import com.blog.entity.Oneword;

/**
 * 每日一句接口
 * @author June
 * @date 2020/05/31
 * @version V1.0
 */
public interface OnewordService {

	//列出最新的七句话
	public List<Oneword> list(Integer size);
}
