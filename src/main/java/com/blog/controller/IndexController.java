package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.dto.BlogTypeTagDTO;
import com.blog.entity.Oneword;
import com.blog.entity.Tag;
import com.blog.service.BlogService;
import com.blog.service.OnewordService;
import com.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/* Created by June on 2020-05-11 */
@Controller
public class IndexController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private TagService tagService;
	@Autowired
	private OnewordService onewordService;

	//博客首页展示的博客数量
	@Value("${index.pageBlogSize}")
	private Integer pageBlogSize;
	//每日一句展示的数量
	@Value("${index.onewordSize}")
	private Integer onewordSize;
	
	
	/**
	 * 跳转到博客首页
	 * @param page 页码
	 * @param size 每页博客的数量
	 * @param model
	 * @return 博客首页
	 */
	@GetMapping("/")
	public String toIndex(@RequestParam(name = "page", defaultValue = "1") Integer page,
			              @RequestParam(name = "size", defaultValue = "10") Integer size, 
			              Model model) {
		size = pageBlogSize;
		//分页
		PageHelper.startPage(page, size);
		List<BlogTypeTagDTO> blogTypeTagDTOs = blogService.listTopBlog();

		PageInfo<BlogTypeTagDTO> pageInfo = new PageInfo<>(blogTypeTagDTOs);
		List<Oneword> onewords = onewordService.listBysize(onewordSize);

		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("onewords", onewords);

		return "index";
	}

	/**
	 * 跳转到博客详情页
	 * @param id
	 * @param model
	 * @return 博客详情页
	 */
	@GetMapping("/blog/{id}")
	public String toBlog(@PathVariable Long id, Model model) {
		BlogTypeTagDTO blogTypeTagDTO = blogService.findAndConvertById(id);
		List<Tag> tags = tagService.listByBlogId(id);

		//增加博客访问次数
		blogService.incViewCntById(id);

		model.addAttribute("blogTypeTagDTO", blogTypeTagDTO);
		model.addAttribute("tags", tags);
		return "blog";
	}

	/**
	 * 搜索博客
	 * @param page
	 * @param size
	 * @param query
	 * @param model
	 * @return 搜索结果页面
	 */
	@PostMapping("/search")
	public String search(@RequestParam(name = "page", defaultValue = "1") Integer page,
			             @RequestParam(name = "size", defaultValue = "3") Integer size, 
			             @RequestParam String query, Model model) {
		//分页
		PageHelper.startPage(page, size);
		List<BlogTypeTagDTO> blogTypeTagDTOs = blogService.listByQuery(query);
		
		PageInfo<BlogTypeTagDTO> pageInfo = new PageInfo<>(blogTypeTagDTOs);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("query", query);
		return "search";
	}
	
	/**
	 * 跳转到听音乐页面
	 * @return 听音乐页面
	 */
	@GetMapping("/music")
	public String toMusic() {
		return "music";
	}

}
