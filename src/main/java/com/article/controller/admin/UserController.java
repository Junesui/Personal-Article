package com.article.controller.admin;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户控制器
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class UserController {

	/**
	 * 跳转到登录页面
	 * @return 登录页面
	 */
	@GetMapping("/toLogin")
	public String toLogin() {
		return "admin/login";
	}

	/**
	 * 注销用户
	 * @param session
	 * @param response
	 * @return 登录页面
	 */
	@GetMapping("/myLogout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("user");
		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		cookie.setPath("/**");
		response.addCookie(cookie);
		return "redirect:/1120/toLogin";
	}

}
