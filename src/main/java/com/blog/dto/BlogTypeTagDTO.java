package com.blog.dto;

import com.blog.entity.Blog;
import com.blog.entity.User;

import lombok.Data;

/**
 * 博客-分类-标签
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Data
public class BlogTypeTagDTO extends Blog {

	//分类d
	private Long typeId;
	//分类名称
	private String typeName;
	
	//标签id
	private Long tagId;
	//标签名称
	private String tagName;
	
	//发布博客页面的多个标签id
	private String tagIds;
	
	//用户id
	private Long userId;
	
	
}
