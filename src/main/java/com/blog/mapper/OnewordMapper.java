package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.entity.Oneword;

/**
 * 每日一句mapperj接口
 * @author June
 * @date 2020/05/31
 * @version V1.0
 */
@Mapper
public interface OnewordMapper {

	List<Oneword> listBysize(Integer size);

	List<Oneword> list();

	void save(Oneword oneword);

	void update(Oneword oneword);

	Oneword findById(Long id);

	void deleteById(Long id);


	
}
