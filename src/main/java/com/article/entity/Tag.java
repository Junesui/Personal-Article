package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

/**
 * 标签
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Data
public class Tag implements Serializable{

	private static final long serialVersionUID = 4896869621607870359L;

	private Long id;
	
	@NotBlank(message = "请输入标签名称")
	@Length(max = 10,message = "标签名称不能超过10个字符")
	private String name;
	
	private String description;
	private Date createTime;
	private Date updateTime;
	private Boolean isDeleted;
}
