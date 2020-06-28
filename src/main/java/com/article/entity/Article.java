package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 文章
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Data
public class Article implements Serializable{
	
	private static final long serialVersionUID = 6492675596264121571L;
	
	private Long id;
	
	@NotBlank(message = "请输入文章标题")
	@Length(max = 60,message = "文章标题不能超过60个字符")
	private String title;
	
	@NotBlank(message = "请输入文章内容")
	private String content;
	
	@NotBlank(message = "请输入文章描述")
	@Length(max = 150,message = "文章标题不能超过150个字符")
	private String description;
	
	@NotBlank(message = "请输入文章首图地址")
	@Length(max = 255,message = "图片地址长度不能超过255个字符")
	private String firstPicture;
	
	@NotBlank(message = "请选择文章分类")
	private Long typeId;
	
	@NotBlank(message = "请选择文章标签")
	private Long userId;
	
	private String flag;
	private String originalLink;
	private Integer viewCount;
	private Integer commentCount;
	private Integer likeCount;
	private Integer downCount;
	private Boolean isAppreciated;
	private Boolean isShared;
	private Boolean isCommented;
	private Boolean isDeleted;
	private Boolean isRecommend;
	private Boolean isTop;
	private Boolean isPrivated;
	private Boolean isPublished;
	private Date createTime;
	private Date updateTime;
}
