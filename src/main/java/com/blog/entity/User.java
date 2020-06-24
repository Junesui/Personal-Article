package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

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
	private String nickname;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	private String avatar;
	private Boolean isManager;
	private Date createTime;
	private Date updateTime;
	private String token;
}
