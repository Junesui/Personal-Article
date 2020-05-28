package com.blog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.blog.entity.Blog;
import com.blog.service.BlogService;

/**
 * 归档展示控制器
 * @author June
 * @date 2020/05/17
 * @version V1.0
 */
@Controller
public class ArchiveShowController {

	@Autowired
	private BlogService blogService;
	
	
	/**
	 * 跳转到归档页面
	 * @param model
	 * @return 归档页面
	 */
	@GetMapping("/archives")
	public String toArchives(Model model) {
		List<Blog> blogs = blogService.archiveBlog();
		model.addAttribute("blogs", blogs);
		return "archives";
	}
}
