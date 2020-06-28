package com.article.dto;

import com.article.entity.Type;

import lombok.Data;

/**
 * 分类和对应的文章数量
 * @author June
 * @date 2020/05/16
 * @version V1.0
 */
@Data
public class TypeArticleNumDTO extends Type{

	//对应的文章个数
	private Long articleNum;
	
}
