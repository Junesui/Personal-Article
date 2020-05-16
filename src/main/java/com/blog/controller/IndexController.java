package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.blog.dto.BlogTypeTagDTO;
import com.blog.dto.TagBlogNumDTO;
import com.blog.dto.TypeBlogNumDTO;
import com.blog.entity.Blog;
import com.blog.entity.Tag;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/* Created by June on 2020-05-11 */
@Controller
public class IndexController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;

	//从配置文件中获取首页展示分类的数量
	@Value("${index.topTypeNum}")
	private Integer topTypeNum;
	//首页展示标签的数量
	@Value("${index.topTagNum}")
	private Integer topTagNum;
	//首页展示推荐博客的数量
	@Value("${index.topRecommendNum}")
	private Integer topRecommendNum;

	/**
	 * 跳转到博客首页
	 * @param page 页码
	 * @param size 每页博客的数量
	 * @param model
	 * @return 博客首页
	 */
	@GetMapping("/")
	public String toIndex(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size, Model model) {
		//分页
		PageHelper.startPage(page, size);
		List<BlogTypeTagDTO> blogTypeTagDTOs = blogService.listTopBlog();

		PageInfo<BlogTypeTagDTO> pageInfo = new PageInfo<>(blogTypeTagDTOs);
		List<TypeBlogNumDTO> typeBlogNumDTOs = typeService.listTopType(topTypeNum);
		List<TagBlogNumDTO> tagBlogNumDTOs = tagService.listTopTag(topTagNum);
		List<Blog> recommendBlogs = blogService.listTopRecommendBlog(topRecommendNum);

		model.addAttribute("typeBlogNumDTOs", typeBlogNumDTOs);
		model.addAttribute("tagBlogNumDTOs", tagBlogNumDTOs);
		model.addAttribute("recommendBlogs", recommendBlogs);
		model.addAttribute("pageInfo", pageInfo);

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
		
		model.addAttribute("blogTypeTagDTO",blogTypeTagDTO );
		model.addAttribute("tags",tags);
		return "blog";
	}

}
