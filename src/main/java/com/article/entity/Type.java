package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 类型
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Data
public class Type implements Serializable{
	
	private static final long serialVersionUID = -2829242768077181120L;

	private Long id;
	
	@NotBlank(message = "请输入分类名称")
	@Length(max = 10,message = "分类名称不能超过10个字符")
	private String name;
	
	private String description;
	private Date createTime;
	private Date updateTime;
	private Boolean isDeleted;
}
