package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 友人链实体类
 * @author June
 * @date 2020/06/21
 * @version V1.0
 */
@Data
public class Friendslink implements Serializable{

	private static final long serialVersionUID = 9115692006126892489L;
	
	public Long id;                 
	public String websiteUrl;        
	public String websiteName;       
	public String websiteDescription;
	public String pictureUrl;            
	public Boolean isShow;            
	public Integer priority;
	public String groups;             
	public Date createTime;        
	public Date updateTime;        
	public Long viewCount;
	
}
