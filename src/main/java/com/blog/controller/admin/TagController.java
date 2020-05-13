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

import com.blog.entity.Tag;
import com.blog.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台标签管理控制器
 * @author June
 * @date 2020/05/13
 * @version V1.0
 */
@Controller
@RequestMapping("/admin")
public class TagController {

	@Autowired
	private TagService tagService;
	
	/**
	 * 跳转到标签管理页面
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return 标签管理页面
	 */
	@GetMapping("/tags")
	public String toTags(@RequestParam(name = "page", defaultValue = "1") Integer pageNum,
					     @RequestParam(name = "size", defaultValue = "3") Integer pageSize, 
					     Model model) {
		//分页
		PageHelper.startPage(pageNum, pageSize);
		List<Tag> tags = tagService.list();
		
		PageInfo<Tag> pageInfo = new PageInfo<>(tags);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/tags";
	}
	
	
	/**
	 * 跳转到标签添加页面
	 * @param model
	 * @return 标签添加页面
	 */
	@GetMapping("/tags/addPage")
	public String toAdd(Model model) {
		model.addAttribute("tag", new Tag());
		return "admin/tag-release";
	}
	
	
	/**
	 * 添加标签
	 * @param tag
	 * @param attributes
	 * @return
	 */
	@PostMapping("/tags")
	public String post(Tag tag,RedirectAttributes attributes) {

		//名称是否重复验证
		if (tagService.findByName(tag.getName()) != null) {
			attributes.addFlashAttribute("nameError", "标签名称已经存在");
			return "redirect:/admin/tags/addPage";
		}
		
		//保存标签
		int tagSaveRst = tagService.save(tag);
		if (tagSaveRst == 1) {
			//添加成功
			attributes.addFlashAttribute("message", "新增成功");
		} else {
			//添加失败
			attributes.addFlashAttribute("message", "新增失败");
		}
		return "redirect:/admin/tags";
	}
	
	
	
}
