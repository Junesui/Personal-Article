package com.blog.dto;

import com.blog.entity.Type;

import lombok.Data;

/**
 * 分类和对应的博客数量
 * @author June
 * @date 2020/05/16
 * @version V1.0
 */
@Data
public class TypeBlogNumDTO extends Type{

	//对应的博客个数
	private Long blogNum;
	
}
