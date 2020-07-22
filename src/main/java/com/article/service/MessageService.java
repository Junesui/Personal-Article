package com.article.service;

import java.util.List;

import com.article.dto.MessageExtDTO;
import com.article.entity.Message;

/**
 * 留言服务层接口
 * @author June
 * @date 2020/06/23
 * @version V1.0
 */
public interface MessageService {

	//获取留言相关信息
	List<MessageExtDTO> listMessage();

	//保存留言
	void save(Message message);

	//获取留言
	List<Message> list();

	//通过内容搜索留言
	List<Message> listBySearch(String content);

	//通过留言id删除留言
	void deleteById(Long id);

}
