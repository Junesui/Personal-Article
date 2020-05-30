package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;



/**
 * 每日一句
 * @author June
 * @date 2020/05/30
 * @version V1.0
 */
@Data
public class Oneword implements Serializable{

	private static final long serialVersionUID = 4345972035373764708L;
	
	private Long id;
	private String picture;
	private String content;
	private Boolean is_deleted;
	private Boolean is_published;
	private Date create_time;
	private Date update_time ;
}
