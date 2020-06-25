package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Tool;
import com.blog.mapper.ToolMapper;
import com.blog.service.ToolService;

/**
 * 工具服务层实现类
 * @author June
 * @date 2020/06/24
 * @version V1.0
 */
@Service
public class ToolServiceImpl implements ToolService{

	@Autowired
	private ToolMapper toolMapper;
	
	@Override
	public List<Tool> list() {
		return toolMapper.list();
	}

	@Override
	public Tool findByName(String name) {
		return toolMapper.findByName(name);
	}

	@Override
	public Tool findByUrl(String url) {
		return toolMapper.findByUrl(url);
	}

	@Override
	public void save(Tool tool) {
		tool.setCreateTime(new Date(System.currentTimeMillis()));
		tool.setUpdateTime(new Date(System.currentTimeMillis()));
		toolMapper.save(tool);
	}

	@Override
	public void update(Tool tool) {
		tool.setUpdateTime(new Date(System.currentTimeMillis()));
		toolMapper.update(tool);
	}

	@Override
	public void deleteById(Integer id) {
		toolMapper.deleteById(id);
	}

	@Override
	public Tool findById(Integer id) {
		return toolMapper.findById(id);
	}

	
}
