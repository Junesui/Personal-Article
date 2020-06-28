package com.article.dto;

import com.article.entity.Tag;

import lombok.Data;

/**
 * 标签和对应的文章数量
 * @author June
 * @date 2020/05/16
 * @version V1.0
 */
@Data
public class TagArticleNumDTO extends Tag {

	//对应的文章个数
	private Long articleNum;
}
