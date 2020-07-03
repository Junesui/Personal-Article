package com.article.service;

import java.util.List;

import com.article.entity.Feedback;

/**
 * 反馈建议服务层接口
 * @author June
 * @date 2020/07/02
 * @version V1.0
 */
public interface FeedbackService {

	//保存反馈
	void save(Feedback feedback);

	//列出所有的反馈
	List<Feedback> list();

	//通过id设置反馈为已修复状态
	void fixById(Long id);

	//通过id设置反馈为未修复状态
	void nofixById(Long id);

	//通过id删除反馈
	void deleteById(Long id);

	//通过搜索条件列出反馈
	List<Feedback> listBySearch(Boolean isFixed);

}
