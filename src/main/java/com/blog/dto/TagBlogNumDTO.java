package com.blog.dto;

import com.blog.entity.Tag;

import lombok.Data;

/**
 * 标签和对应的博客数量
 * @author June
 * @date 2020/05/16
 * @version V1.0
 */
@Data
public class TagBlogNumDTO extends Tag {

	//对应的博客个数
	private Long blogNum;
}
