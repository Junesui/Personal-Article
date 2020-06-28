package com.article.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.article.dto.MessageExtDTO;
import com.article.entity.Message;
import com.article.mapper.MessageMapper;
import com.article.service.MessageService;

/**
 * 留言服务层实现类
 * @author June
 * @date 2020/06/23
 * @version V1.0
 */
@Service
public class MessageServiceImpl implements MessageService{

	@Autowired
	private MessageMapper messageMapper;
	
	//存放迭代找出的所有子代的集合
	private List<Message> replys = new ArrayList<>();
	
	@Override
	public List<MessageExtDTO> listMessage() {
		List<MessageExtDTO> messageExtDTOs = messageMapper.listMessage();
		for (MessageExtDTO dto : messageExtDTOs) {
			//找到留言的回复
			List<MessageExtDTO> replyMessages = messageMapper.listReplyMessagesByParentId(dto.getId());
			combineChildren(replyMessages,dto.getNickname());
			dto.setReplys(replys);
			replys = new ArrayList<>();
		}
		
		return messageExtDTOs;
	}
	
	/**
	 * 遍历并迭代
	 * @param comments
	 * @param pNickname
	 */
	private void combineChildren(List<MessageExtDTO> messages, String pNickname) {
		if (messages.size() > 0) {
			for (MessageExtDTO message : messages) {
				message.setParentNickname(pNickname);
				replys.add(message);
				recursively(message, message.getNickname());
			}
		}
	}

	/**
	 * 递归迭代，剥洋葱
	 * @param comment 被迭代的对象
	 * @param pNickname 父评论昵称
	 */
	private void recursively(MessageExtDTO message, String pNickname) {

		List<MessageExtDTO> replyMessages = messageMapper.listReplyMessagesByParentId(message.getId());

		if (replyMessages.size() > 0) {
			for (MessageExtDTO reMessage : replyMessages) {
				reMessage.setParentNickname(pNickname);
				replys.add(reMessage);
				recursively(reMessage, reMessage.getNickname());
			}
		}

	}

	@Override
	public void save(Message message) {
		//留言的父id设置为空
		if (message.getParentId() == -1) {
			message.setParentId(null);
		}else {
			messageMapper.incReplyCntById(message.getParentId());
		}
		message.setCreateTime(new Date(System.currentTimeMillis()));
		message.setReplyCount(0);
		
		messageMapper.save(message);
	}

	
}
