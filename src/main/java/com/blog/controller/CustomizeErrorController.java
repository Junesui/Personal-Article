package com.blog.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常控制器  【备注：如果不写此控制器，直接在templates目录下创建error文件夹，在error文件夹中创建「4xx.html,5xx.html,error.html」，发生相匹配的异常时会自动跳转到相应的页面】
 * @author June
 * @date 2020/05/24
 * @version V1.0
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class CustomizeErrorController implements ErrorController {

	/**
	 * 跳转到error页面
	 */
	@Override
	public String getErrorPath() {
		return "/error";
	}

	/**
	 * 异常判断并跳转到error
	 * @param request
	 * @param model
	 * @return error页面
	 */
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public ModelAndView errorHtml(HttpServletRequest request, Model model) {
		HttpStatus status = getStatus(request);

		//前端页面异常
		if (status.is4xxClientError()) {
			model.addAttribute("message", "访问的页面辞职了，世界那么大，他想去看看...");
		}
		
		//服务器内部异常
		if (status.is5xxServerError()) {
			model.addAttribute("message", "服务器罢工了，可以去向管理员举报哦！");
		}

		return new ModelAndView("error");
	}

	/**
	 * 获取错误状态码
	 * @param request
	 * @return
	 */
	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		try {
			return HttpStatus.valueOf(statusCode);
		} catch (Exception ex) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}

}
