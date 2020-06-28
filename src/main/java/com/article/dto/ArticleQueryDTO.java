package com.article.dto;

import lombok.Data;

/**
 * 文章查询dto
 * @author June
 * @date 2020/05/13
 * @version V1.0
 */
@Data
public class ArticleQueryDTO {

	private String title;
	private Long typeId;
	private Boolean isRecommend;
}
