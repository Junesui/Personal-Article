package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

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
	private String name;
	private String description;
	private Date createTime;
	private Date updateTime;
	private String reserve1;
	private String reserve2;
	private Integer reserve3;
	private Integer reserve4;
}
