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
	public List<Oneword> listBysize(Integer size);

	//列出所有的每日一句
	public List<Oneword> list();

	//保存或更新每日一句
	public void saveOrUpdate(Oneword oneword);

	//通过id查找每日一句
	public Oneword findById(Long id);

	//通过id设置删除标志
	public void updateDel(Long id);
}
