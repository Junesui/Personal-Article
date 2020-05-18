package com.blog.dto;

import java.util.ArrayList;
import java.util.List;

import com.blog.entity.Comment;
import com.blog.entity.User;

import lombok.Data;

/**
 * 评论扩展DTO
 * @author June
 * @date 2020/05/18
 * @version V1.0
 */
@Data
public class CommentExtDTO extends Comment{

	//子评论集合
	private List<Comment> replyComments = new ArrayList<Comment>();
	
	//父评论昵称
	private String parentNickname;
	
	//用户
	private User user;
}
