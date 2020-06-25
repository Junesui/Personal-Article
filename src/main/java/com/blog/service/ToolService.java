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

	//通过id找到工具
	Tool findById(Integer id);
	
	//列出所有的工具
	List<Tool> list();

	//通过名称查找工具
	Tool findByName(String name);

	//通过链接查找工具
	Tool findByUrl(String url);

	//保存工具
	void save(Tool tool);
	
	//更新工具
	void update(Tool tool);

	//通过id删除工具
	void deleteById(Integer id);

}
