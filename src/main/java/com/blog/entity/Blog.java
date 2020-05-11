package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 博客
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Data
public class Blog implements Serializable{
	
	private static final long serialVersionUID = 6492675596264121571L;
	
	private Long id;
	private String title;
	private String content;
	private String firstPicture;
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
	private Long typeId;
	private Long userId;
	private String reserve1;
	private String reserve2;
	private Integer reserve3;
	private Integer reserve4;
}
