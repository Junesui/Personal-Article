package com.blog.dto;

import lombok.Data;

/**
 * 博客查询dto
 * @author June
 * @date 2020/05/13
 * @version V1.0
 */
@Data
public class BlogQueryDTO {

	private String title;
	private Long typeId;
	private Boolean isRecommend;
}
