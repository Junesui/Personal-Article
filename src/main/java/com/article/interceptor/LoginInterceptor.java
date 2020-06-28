package com.article.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.article.entity.User;
import com.article.service.UserService;

/**
 * 登录拦截器
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if ("token".equals(cookie.getName())) {
					String token = cookie.getValue();

					//通过token查找用户
					User user = userService.findByToken(token);
					if (user != null || request.getSession().getAttribute("user") != null) {
						user.setPassword(null);
						request.getSession().setAttribute("user", user);
						return true;
					}
					break;
				}
			}
		}
		response.sendRedirect("/1120/login");
		return false;
	}

}
