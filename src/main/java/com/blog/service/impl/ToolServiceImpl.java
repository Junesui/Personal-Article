package com.blog.service.impl;

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

	
}
