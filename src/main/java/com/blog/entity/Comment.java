package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 评论
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Data
public class Comment implements Serializable{

	private static final long serialVersionUID = 3923574361077359564L;
	
	private Long id;
	private String nickname;
	private String email;
	private String avatar;
	private String content;
	private Date createTime;
	private Integer replyCount;
	private Integer likeCount;
	private Integer downCount;
	private Long blogId;
	private Long parentId;
	private String reserve1;
	private String reserve2;
	private Integer reserve3;
	private Integer reserve4;
}
