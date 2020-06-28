package com.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 关于我展示控制器
 * @author June
 * @date 2020/05/17
 * @version V1.0
 */
@Controller
public class AboutShowController {

	
	/**
	 * 跳转到关于我页面
	 * @return 关于我页面
	 */
	@GetMapping("/about")
	public String about() {
		return "about";
	}

}
