package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 用户
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Data
public class User implements Serializable{

	private static final long serialVersionUID = 8091197205740959431L;
	
	private Long id;
	
	@NotBlank(message = "请输入昵称")
	@Length(max = 30,message = "姓名不能超过30个字符")
	private String nickname;
	
	@NotBlank(message = "请输入用户名")
	@Length(max = 30,message = "用户名不能超过30个字符")
	private String username;
	
	@NotBlank(message = "请输入密码")
	private String password;
	
	@Email(message = "请输入正确的邮箱格式")
	private String email;
	
	private String phoneNumber;
	private String address;
	private String avatar;
	private Boolean isManager;
	private Integer level;
	private Date createTime;
	private Date updateTime;
	private String token;
	private Date lastLoginTime;
}
