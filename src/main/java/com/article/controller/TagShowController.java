package com.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.article.dto.ArticleTypeTagDTO;
import com.article.dto.TagArticleNumDTO;
import com.article.service.ArticleService;
import com.article.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 标签展示控制器
 * @author June
 * @date 2020/05/17
 * @version V1.0
 */
@Controller
public class TagShowController {

	@Autowired
	private TagService tagService;
	@Autowired
	private ArticleService articleService;
	
	//标签页展示的文章数量
	@Value("${tags.pageArticleSize}")
	private Integer pageArticleSize;

	/**
	 * 跳转到标签展示页面
	 * @param page
	 * @param size
	 * @param id
	 * @param model
	 * @return 标签展示页面
	 */
	@GetMapping("/tags/{id}")
	public String toTags(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size, @PathVariable Long id, Model model) {
		size = pageArticleSize;
		//统计有文章的标签总数
		Long tagCnt = tagService.count();
		
		List<TagArticleNumDTO> tags = tagService.listTagShow();
		if (id == -1) {
			id = tags.get(0).getId();
		}

		//分页
		PageHelper.startPage(page, size);
		List<ArticleTypeTagDTO> articleTypeTagDTO = articleService.listTagArticleByTagId(id);
		
		PageInfo<ArticleTypeTagDTO> pageInfo = new PageInfo<>(articleTypeTagDTO);

		model.addAttribute("tagCnt", tagCnt);
		model.addAttribute("tags", tags);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("activeTagId", id);
		return "tags";
	}

}
