package com.article.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.article.entity.Oneword;
import com.article.service.OnewordService;
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
	public String toOnewords(@RequestParam(name = "page", defaultValue = "1") Integer page,
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
	 * 发布每日一句
	 * @param oneword
	 * @param attributes
	 * @return 文章管理页面
	 */
	@PostMapping("/onewords")
	public String post(@Validated Oneword oneword, BindingResult result,RedirectAttributes attributes) {
		
		//字段验证
		if (result.hasErrors()) {
			return "admin/oneword-release";
		}
		
		onewordService.saveOrUpdate(oneword);
		attributes.addFlashAttribute("message", "操作成功");
		return "redirect:/1120/onewords";
	}

	/**
	 * 跳转到每日一句编辑页面
	 * @param id 每日一句id
	 * @param model
	 * @return 每日一句编辑页面
	 */
	@GetMapping("/onewords/edit/{id}")
	public String onewordEdit(@PathVariable("id") Long id, Model model) {
		//通过id查找每日一句
		Oneword oneword = onewordService.findById(id);
	
		model.addAttribute("oneword", oneword);
		return "admin/oneword-release";
	}

	/**
	 * 根据id给每日一句设置删除标志
	 * @param id
	 * @param model
	 * @return 每日一句管理页面
	 */
	@GetMapping("/onewords/delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes attributes) {
		onewordService.delete(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/onewords";
	}

}
