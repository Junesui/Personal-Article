package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.entity.Tool;

/**
 * 工具数据库层接口
 * @author June
 * @date 2020/06/24
 * @version V1.0
 */
@Mapper
public interface ToolMapper {

	List<Tool> list();

	Tool findByName(String name);

	Tool findByUrl(String url);

	void save(Tool tool);

	void update(Tool tool);

	void deleteById(Integer id);

	Tool findById(Integer id);

}
