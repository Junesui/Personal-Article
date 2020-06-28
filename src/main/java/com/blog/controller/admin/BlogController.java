package com.blog.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.dto.BlogQueryDTO;
import com.blog.dto.BlogTypeTagDTO;
import com.blog.entity.Tag;
import com.blog.entity.User;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.blog.util.StringAndListConvertUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台博客管理控制器
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;


	//博客后台管理每页展示的博客数量
	@Value("${admin.pageBlogSize}")
	private Integer pageBlogSize;
	
	
	/**
	 * 跳转到后台首页
	 * @return 后台首页
	 */
	@GetMapping("/index")
	public String toIndex() {
		return "admin/index";
	}

	/**
	 * 跳转到博客管理页面
	 * @param pageNum 页码
	 * @param pageSize 每页数据的数量
	 * @param model
	 * @return 博客管理页面
	 */
	@GetMapping("/blogs")
	public String toBlogs(@RequestParam(name = "page", defaultValue = "1") Integer page,
			   		      @RequestParam(name = "size", defaultValue = "10") Integer size, 
			   		      Model model) {
		size = pageBlogSize;
		//分页
		PageHelper.startPage(page, size);
		List<BlogTypeTagDTO> blogTypeDTOs = blogService.listBlogAndType();

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
	public String post(BlogTypeTagDTO blogTypeTagDTO, HttpSession session, RedirectAttributes attributes) {

		User user = (User) session.getAttribute("user");
		blogTypeTagDTO.setUserId(user.getId());

		blogService.saveOrUpdate(blogTypeTagDTO);

		attributes.addFlashAttribute("message", "操作成功");
		return "redirect:/1120/blogs";
	}

	/**
	 * 后台查找博客
	 * @param pageNum
	 * @param pageSize
	 * @param blogQueryDTO
	 * @param model
	 * @return 博客管理页面的博客列表片段
	 */
	@PostMapping("/blogs/search")
	public String search(@RequestParam(name = "page", defaultValue = "1") Integer page,
			             @RequestParam(name = "size", defaultValue = "10") Integer size, 
			             BlogQueryDTO blogQueryDTO, Model model) {
		size = pageBlogSize;
		//分页
		PageHelper.startPage(page, size);
		List<BlogTypeTagDTO> dto = blogService.listBySearch(blogQueryDTO);
		PageInfo<BlogTypeTagDTO> pageInfo = new PageInfo<>(dto);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/blogs :: blogList";
	}

	/**
	 * 跳转到博客编辑页面
	 * @param id
	 * @param model
	 * @return 博客编辑页面
	 */
	@GetMapping("/blogs/edit/{id}")
	public String blogEdit(@PathVariable Long id, Model model) {
		//通过博客id查找博客和分类
		BlogTypeTagDTO blogTypeTagDTO = blogService.findBlogAndTypeById(id);

		//通过博客id查找对应的标签id
		List<Tag> tags = tagService.listByBlogId(id);
		List<Long> tagList = new ArrayList<Long>();
		for (Tag tag : tags) {
			tagList.add(tag.getId());
		}
		//列表tagids转换为字符串
		String tagIds = StringAndListConvertUtils.toString(tagList);

		model.addAttribute("blogTypeTagDTO", blogTypeTagDTO);
		model.addAttribute("tagIds", tagIds);
		model.addAttribute("types", typeService.list());
		model.addAttribute("tags", tagService.list());
		return "admin/blog-release";
	}

	/**
	 * 根据id删除博客
	 * @param id
	 * @param model
	 * @return 博客管理页面
	 */
	@GetMapping("/blogs/delete/{id}")
	public String blogDelete(@PathVariable Long id, RedirectAttributes attributes) {
		blogService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/blogs";
	}

}
