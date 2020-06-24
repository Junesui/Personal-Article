package com.blog.controller.admin;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.entity.User;
import com.blog.service.UserService;

/**
 * 用户控制器
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 跳转到登录页面
	 * @return 登录页面
	 */
	@GetMapping("/login")
	public String toLogin() {
		return "admin/login";
	}

	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @param session
	 * @param attributes
	 * @param response
	 * @return 后台首页
	 */
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
			RedirectAttributes attributes, HttpServletResponse response) {
		User user = userService.findByUsernameAndPassword(username, password);
		if (user != null) {
			//密码清空
			user.setPassword(null);

			//存cookie
			String token = UUID.randomUUID().toString();
			userService.updateTokenById(token, user.getId());
			Cookie cookie = new Cookie("token", token);
			cookie.setMaxAge(50 * 365 * 24 * 60 * 60);
			response.addCookie(cookie);

			//存session
			session.setAttribute("user", user);
			return "redirect:/1120/index";
		} else {
			attributes.addFlashAttribute("message", "用户名或密码错误");
			return "redirect:/1120/login";
		}
	}

	/**
	 * 注销用户
	 * @param session
	 * @param response
	 * @return 登录页面
	 */
	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
		session.removeAttribute("user");
		Cookie cookie = new Cookie("token", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/1120/login";
	}

}
