package com.article.entity;

import java.io.Serializable;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 反馈建议
 * @author June
 * @date 2020/07/01
 * @version V1.0
 */
@Data
public class Feedback implements Serializable{
	
	private static final long serialVersionUID = 6906802099302300324L;

	public Long id;
	
	@Length(max = 255,message = "链接长度不能超过255个字符")
	public String url;
	
	@Length(max = 500,message = "内容长度不能超过500个字符")
	public String content;
	
	@Email(message = "请输入正确的邮箱格式")
	public String email;
	
	public String createTime;
	public String isFixed;
	public String isDeleted;
}
