package com.blog.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.blog.exception.CustomizeErrorCode;
import com.blog.exception.CustomizeException;

/**
 * 自定义异常处理器
 * @author June
 * @date 2020/05/27
 * @version V1.0
 */
@ControllerAdvice
public class CustomizeExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handle(Throwable e, Model model) {
		
		if (e instanceof CustomizeException) {
			// 自定义异常
			model.addAttribute("message", e.getMessage());
		} else {
			model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMesssage());
		}
		
		return new ModelAndView("error");
	}
}
