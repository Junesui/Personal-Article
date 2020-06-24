package com.blog.entity;

import java.io.Serializable;
import java.util.Date;

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
	private String nickname;
	private String email;
	private String avatar;
	private String content;
	private Date createTime;
	private Integer replyCount;
	private Long parentId;
	private Boolean isManager;
}
