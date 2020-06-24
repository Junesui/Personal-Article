package com.blog.service;

import java.util.List;

import com.blog.dto.MessageExtDTO;
import com.blog.entity.Message;

/**
 * 留言服务层接口
 * @author June
 * @date 2020/06/23
 * @version V1.0
 */
public interface MessageService {

	//获取留言
	List<MessageExtDTO> listMessage();

	//保存留言
	void save(Message message);

}
