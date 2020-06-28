package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 网站信息实体类
 * @author June
 * @date 2020/06/26
 * @version V1.0
 */
@Data
public class Siteinfo implements Serializable{

	private static final long serialVersionUID = 6401005966689072899L;
	
	public Date lastloginTime;
	public Long viewCount;
}
