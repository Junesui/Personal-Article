package com.article.service;

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

}
