package com.article.mapper;

import java.util.List;

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

	List<Feedback> list();

	void fixById(Long id);

	void nofixById(Long id);

	void deleteById(Long id);

	List<Feedback> listBySearch(Boolean isFixed);

}
