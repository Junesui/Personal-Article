package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 留言
 * @author June
 * @date 2020/06/23
 * @version V1.0
 */
@Data
public class Message implements Serializable{

	private static final long serialVersionUID = 4919102505027695997L;
	
	private Long id;
	
	@NotBlank(message = "请输入姓名")
	@Length(max = 15,message = "姓名不能超过15个字符")
	private String nickname;
	
	@Email(message = "请输入正确的邮箱格式")
	private String email;
	
	@NotBlank(message = "请输入留言内容")
	private String content;
	
	private String avatar;
	private Date createTime;
	private Integer replyCount;
	private Long parentId;
	private Boolean isManager;
	private Boolean isDeleted;
}
