package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 工具
 * @author June
 * @date 2020/06/24
 * @version V1.0
 */
@Data
public class Tool implements Serializable{
	
	private static final long serialVersionUID = 3458578602089193930L;

	private Integer id;
	private String name;
	private String url;
	private Date createTime;
	private Date updateTime;
	
}
