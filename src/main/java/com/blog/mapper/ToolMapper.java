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

}
