package com.blog.controller.admin;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.dto.BlogQueryDTO;
import com.blog.dto.BlogTypeTagDTO;
import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台博客管理控制器
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Controller
@RequestMapping("/admin")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;
	
	
	/**
	 * 跳转到后台首页
	 * @return
	 */
	@GetMapping("/index")
	public String index() {
		return "/admin/index";
	}
	
	/**
	 * 跳转到博客管理页面
	 * @param pageNum 页码
	 * @param pageSize 每页数据的数量
	 * @param model
	 * @return 博客管理页面
	 */
	@GetMapping("/blogs")
	public String toBlogs(@RequestParam(name = "page",defaultValue = "1") Integer pageNum,
						@RequestParam(name = "size",defaultValue = "3") Integer pageSize,
						Model model) {
		//分页
		PageHelper.startPage(pageNum, pageSize);
		List<BlogTypeTagDTO> blogTypeDTOs = blogService.listBlogAndTypeName();
		
		PageInfo<BlogTypeTagDTO> pageInfo = new PageInfo<>(blogTypeDTOs);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", typeService.list());
		return "admin/blogs";
	}
	
	/**
	 * 跳转到博客发布页面
	 * @return
	 */
	@GetMapping("/blogs/add")
	public String blogsAdd(Model model) {
		model.addAttribute("blogTypeTagDTO", new BlogTypeTagDTO());
		model.addAttribute("types", typeService.list());
		model.addAttribute("tags", tagService.list());
		return "admin/blog-release";
	}
	
	/**
	 * 发布博客
	 * @param blog
	 * @param session
	 * @param attributes
	 * @return 博客管理页面
	 */
	@PostMapping("/blogs")
	public String post(BlogTypeTagDTO blogTypeTagDTO,HttpSession session,RedirectAttributes attributes) {

		User user = (User)session.getAttribute("user");
		blogTypeTagDTO.setUserId(user.getId());
		
		int i = blogService.saveOrUpdate(blogTypeTagDTO);
		
		if (i == 1) {
			attributes.addFlashAttribute("message", "操作成功");
		}else {
			attributes.addFlashAttribute("message", "操作失败");
		}
		return "redirect:/admin/blogs";
	}
	
	
	@PostMapping("/blogs/search")
	public String search(@RequestParam(name = "page", defaultValue = "1") Integer pageNum,
			             @RequestParam(name = "size", defaultValue = "3") Integer pageSize, 
			             BlogQueryDTO blogQueryDTO, Model model) {
		//分页
		PageHelper.startPage(pageNum, pageSize);
		List<BlogTypeTagDTO> dto = blogService.listBySearch(blogQueryDTO);
		PageInfo<BlogTypeTagDTO> pageInfo = new PageInfo<>(dto);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/blogs :: blogList";
	}
	
	
}
