package com.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.article.entity.Article;
import com.article.service.ArticleService;

/**
 * 时间轴控制器
 * @author June
 * @date 2020/05/17
 * @version V1.0
 */
@Controller
public class ArchiveShowController {

	@Autowired
	private ArticleService articleService;
	
	
	/**
	 * 跳转到时间轴页面
	 * @param model
	 * @return 时间轴页面
	 */
	@GetMapping("/archives")
	public String toArchives(Model model) {
		List<Article> articles = articleService.archiveArticle();
		model.addAttribute("articles", articles);
		return "archives";
	}
}
