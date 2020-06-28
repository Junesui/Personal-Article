package com.article.dto;

import java.util.ArrayList;
import java.util.List;

import com.article.entity.Comment;
import com.article.entity.Message;
import com.article.entity.User;

import lombok.Data;

/**
 * 留言扩展DTO
 * @author June
 * @date 2020/06/23
 * @version V1.0
 */
@Data
public class MessageExtDTO extends Message{

	//回复集合
	private List<Message> replys = new ArrayList<Message>();
	
	//父留言昵称
	private String parentNickname;
	
	//用户
	private User user;
}
