package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

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
	
	@NotBlank(message = "请输入姓名")
	@Length(max = 15,message = "姓名不能超过15个字符")
	private String nickname;
	
	@Email(message = "请输入正确的邮箱格式")
	private String email;

	@NotBlank(message = "请输入评论内容")
	private String content;
	
	private String avatar;
	private Date createTime;
	private Boolean isDeleted;
	private Integer replyCount;
	private Integer likeCount;
	private Integer downCount;
	
	private Long parentId;
	private Boolean isManager;
}
