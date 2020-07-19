package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;



/**
 * 每日一句
 * @author June
 * @date 2020/05/30
 * @version V1.0
 */
@Data
public class Oneword implements Serializable{

	private static final long serialVersionUID = 4345972035373764708L;
	
	private Long id;
	
	@Length(max = 255,message = "图片地址长度不能超过255个字符")
	private String picture;
	
	@NotBlank(message = "请输入一句内容")
	@Length(max = 60,message = "一句内容不能超过60个字符")
	private String content;
	
	private Boolean isDeleted;
	private Boolean isPublished;
	private Date createTime;
	private Date updateTime ;
}
