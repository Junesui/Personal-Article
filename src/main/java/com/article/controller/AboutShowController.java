package com.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.article.entity.Tool;
import com.article.service.ToolService;

/**
 * 关于我展示控制器
 * @author June
 * @date 2020/05/17
 * @version V1.0
 */
@Controller
public class AboutShowController {

	@Autowired
	private ToolService toolService;

	
	/**
	 * 跳转到关于我页面
	 * @return 关于我页面
	 */
	@GetMapping("/about")
	public String about(Model model) {
		List<Tool> tools = toolService.list();
		model.addAttribute("tools", tools);
		return "about";
	}

}
