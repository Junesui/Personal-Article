package com.blog.service;

import java.util.List;

import com.blog.entity.Tool;

/**
 * 工具服务层接口
 * @author June
 * @date 2020/06/24
 * @version V1.0
 */
public interface ToolService {

	//列出所有的工具
	List<Tool> list();

}
