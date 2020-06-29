package com.article.dto;

import javax.validation.constraints.NotBlank;

import com.article.entity.Article;

import lombok.Data;

/**
 * 文章-分类-标签
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Data
public class ArticleTypeTagDTO extends Article {

	//分类d
	//private Long typeId;
	//分类名称
	private String typeName;
	
	//标签id
	private Long tagId;
	//标签名称
	private String tagName;
	
	//发布文章页面的多个标签id
	@NotBlank(message = "请选择文章标签")
	private String tagIds;
	
	//用户id
	private Long userId;
	//用户头像
	private String userAvatar;
	//用户昵称
	private String userNickname;
	
}
