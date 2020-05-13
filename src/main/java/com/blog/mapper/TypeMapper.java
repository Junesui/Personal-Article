package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.entity.Type;

/**
 * 分类mapper接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Mapper
public interface TypeMapper {

	List<Type> list();

	Type findByName(String name);

	int save(Type type);

}
