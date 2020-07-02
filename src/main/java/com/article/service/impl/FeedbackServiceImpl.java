package com.article.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.article.entity.Feedback;
import com.article.mapper.FeedbackMapper;
import com.article.service.FeedbackService;

/**
 * 反馈建议服务层实现类
 * @author June
 * @date 2020/07/02
 * @version V1.0
 */
@Service
public class FeedbackServiceImpl implements FeedbackService{

	@Autowired
	private FeedbackMapper feedbackMapper;
	
	@Override
	public void save(Feedback feedback) {
		feedback.setCreateTime(new Date(System.currentTimeMillis()));
		feedbackMapper.save(feedback);
	}

}
