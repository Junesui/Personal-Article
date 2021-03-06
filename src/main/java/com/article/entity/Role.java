package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 角色实体类
 * @author June
 * @date 2020/08/26
 * @version V1.0
 */
@Data
public class Role implements Serializable{

	private static final long serialVersionUID = 5998064142777865090L;
	
	public Integer id;
	public String name;
	public Date createTime;
	public Date updateTime;
	public String description;
}
