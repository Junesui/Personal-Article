package com.blog.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.entity.Type;
import com.blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台分类管理控制器
 * @author June
 * @date 2020/05/13
 * @version V1.0
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

	@Autowired
	private TypeService typeService;
	
	
	/**
	 * 跳转到分类管理页面
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return 分类管理页面
	 */
	@GetMapping("/types")
	public String toTypes(@RequestParam(name = "page", defaultValue = "1") Integer pageNum,
			@RequestParam(name = "size", defaultValue = "3") Integer pageSize, Model model) {
		//分页
		PageHelper.startPage(pageNum, pageSize);
		List<Type> types = typeService.list();

		PageInfo<Type> pageInfo = new PageInfo<>(types);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/types";
	}
	
	
	/**
	 * 跳转到分类添加页面
	 * @param model
	 * @return 分类添加页面
	 */
	@GetMapping("/types/addPage")
	public String toAdd(Model model) {
		model.addAttribute("type", new Type());
		return "admin/type-release";
	}
	
	
	@PostMapping("/types")
	public String post(Type type, RedirectAttributes attributes) {

		//名称是否重复验证
		if (typeService.findByName(type.getName()) != null) {
			attributes.addFlashAttribute("nameError", "分类名称已经存在");
			return "redirect:/admin/types/addPage";
		}

		int typeSaveRst = typeService.save(type);
		if (typeSaveRst == 1) {
			//添加成功
			attributes.addFlashAttribute("message", "新增成功");
		} else {
			//添加失败
			attributes.addFlashAttribute("message", "新增失败");
		}
		return "redirect:/admin/types";
	}
	
	
	
	
}
