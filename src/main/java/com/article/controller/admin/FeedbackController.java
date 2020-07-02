package com.article.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.article.service.FeedbackService;

/**
 * 反馈建议后台管理
 * @author June
 * @date 2020/07/02
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	
	
}
