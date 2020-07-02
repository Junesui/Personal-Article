package com.article.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.article.entity.Feedback;

/**
 * 反馈建议数据库层接口
 * @author June
 * @date 2020/07/02
 * @version V1.0
 */
@Mapper
public interface FeedbackMapper {

	void save(Feedback feedback);

	Integer count();

}
