package com.blog.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String toTypes(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "3") Integer size, Model model) {
		//分页
		PageHelper.startPage(page, size);
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

	/**
	 * 跳转到分类编辑页面
	 * @param id
	 * @param model
	 * @return 分类编辑页面
	 */
	@GetMapping("/types/edit/{id}")
	public String toEdit(@PathVariable Long id, Model model) {
		model.addAttribute("type", typeService.findById(id));
		return "admin/type-release";
	}

	/**
	 * 新增分类
	 * @param type
	 * @param attributes
	 * @return 分类管理页面
	 */
	@PostMapping("/types")
	public String post(Type type, RedirectAttributes attributes, Model model) {
		//名称是否重复验证
		if (typeService.findByName(type.getName()) != null) {
			model.addAttribute("nameError", "分类名称已经存在");
			return "/admin/type-release";
		}

		typeService.save(type);
		attributes.addFlashAttribute("message", "新增成功");
		return "redirect:/admin/types";
	}

	/**
	 * 编辑分类
	 * @param type
	 * @param result
	 * @param id
	 * @param attributes
	 * @return 分类管理页面
	 */
	@PostMapping("/types/{id}")
	public String editPost(Type type, @PathVariable Long id, RedirectAttributes attributes, Model model) {

		//名称是否重复验证
		if (typeService.findByName(type.getName()) != null) {
			model.addAttribute("nameError", "分类名称已经存在");
			return "/admin/type-release";
		}

		typeService.update(type);
		attributes.addFlashAttribute("message", "更新成功");
		return "redirect:/admin/types";
	}

	/**
	 * 删除分类
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/types/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		typeService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/admin/types";
	}

}