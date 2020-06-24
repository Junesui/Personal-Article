package com.blog.controller.admin;

import java.util.List;

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

import com.blog.entity.Oneword;
import com.blog.service.OnewordService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 每日一句管理控制器
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class OnewordController {

	@Autowired
	private OnewordService onewordService;

	@Value("${admin.pageOnewordSize}")
	private Integer pageOnewordSize;
	
	
	/**
	 * 跳转到后台每日一句管理页
	 * @param pageNum 页码
	 * @param pageSize 每页数据的数量
	 * @param model
	 * @return 每日一句管理页
	 */
	@GetMapping("/onewords")
	public String toBlogs(@RequestParam(name = "page", defaultValue = "1") Integer page,
			   		      @RequestParam(name = "size", defaultValue = "10") Integer size, 
			   		      Model model) {
		size = pageOnewordSize;
		//分页
		PageHelper.startPage(page, size);
		List<Oneword> onewords = onewordService.list();

		PageInfo<Oneword> pageInfo = new PageInfo<>(onewords);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/oneword";
	}

	/**
	 * 跳转到每日一句发布页面
	 * @return
	 */
	@GetMapping("/onewords/addPage")
	public String toAddpage(Model model) {
		model.addAttribute("oneword", new Oneword());
		return "admin/oneword-release";
	}

	/**
	 * 发布博客
	 * @param blog
	 * @param session
	 * @param attributes
	 * @return 博客管理页面
	 */
	@PostMapping("/onewords")
	public String post(Oneword oneword, RedirectAttributes attributes) {
		onewordService.saveOrUpdate(oneword);
		attributes.addFlashAttribute("message", "操作成功");
		return "redirect:/1120/onewords";
	}

	/**
	 * 跳转到每日一句编辑页面
	 * @param id
	 * @param model
	 * @return 每日一句编辑页面
	 */
	@GetMapping("/onewords/edit/{id}")
	public String blogEdit(@PathVariable Long id, Model model) {
		//通过id查找每日一句
		Oneword oneword = onewordService.findById(id);
	
		model.addAttribute("oneword", oneword);
		return "admin/oneword-release";
	}

	/**
	 * 根据id删除每日一句
	 * @param id
	 * @param model
	 * @return 每日一句管理页面
	 */
	@GetMapping("/onewords/delete/{id}")
	public String blogDelete(@PathVariable Long id, RedirectAttributes attributes) {
		onewordService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/onewords";
	}

}
