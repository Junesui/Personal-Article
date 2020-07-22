package com.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.article.dto.MessageExtDTO;
import com.article.entity.Message;

/**
 * 留言数据库层接口
 * @author June
 * @date 2020/06/23
 * @version V1.0
 */
@Mapper
public interface MessageMapper {

	List<MessageExtDTO> listMessage();

	List<MessageExtDTO> listReplyMessagesByParentId(Long parentId);

	void incReplyCntById(Long id);

	void save(Message message);

	Integer count();

	List<Message> list();

	List<Message> listBySearch(String content);

	void deleteById(Long id);

}
