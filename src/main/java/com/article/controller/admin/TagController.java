package com.article.controller.admin;

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

import com.article.entity.Tag;
import com.article.service.TagService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台标签管理控制器
 * @author June
 * @date 2020/05/13
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class TagController {

	@Autowired
	private TagService tagService;
	
	//标签后台管理页每页展示的数量
	@Value("${admin.tagSize}")
	private Integer tagSize;
	
	
	/**
	 * 跳转到标签管理页面
	 * @param pageNum
	 * @param pageSize
	 * @param model
	 * @return 标签管理页面
	 */
	@GetMapping("/tags")
	public String toTags(@RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size, Model model) {
		size = tagSize;
		//分页
		PageHelper.startPage(page, size);
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
	 * 跳转到标签编辑页面
	 * @param id
	 * @param model
	 * @return 标签编辑页面
	 */
	@GetMapping("/tags/edit/{id}")
	public String toEdit(@PathVariable Long id, Model model) {
		model.addAttribute("tag", tagService.findById(id));
		return "admin/tag-release";
	}

	/**
	 * 添加标签
	 * @param tag
	 * @param attributes
	 * @return
	 */
	@PostMapping("/tags")
	public String post(Tag tag, RedirectAttributes attributes, Model model) {

		//名称是否重复验证
		if (tagService.findByName(tag.getName()) != null) {
			model.addAttribute("nameError", "标签名称已经存在");
			return "admin/tag-release";
		}

		//保存标签
		tagService.save(tag);
		attributes.addFlashAttribute("message", "新增成功");
		return "redirect:/1120/tags";
	}

	/**
	 * 编辑标签
	 * @param tag
	 * @param id
	 * @param attributes
	 * @param model
	 * @return 标签管理页面
	 */
	@PostMapping("/tags/{id}")
	public String editpost(Tag tag, @PathVariable Long id, RedirectAttributes attributes, Model model) {

		//名称是否重复验证
		if (tagService.findByName(tag.getName()) != null) {
			model.addAttribute("nameError", "分类名称已经存在");
			return "admin/tag-release";
		}

		tagService.update(tag);
		attributes.addFlashAttribute("message", "更新成功");
		return "redirect:/1120/tags";
	}

	/**
	 * 删除标签
	 * @param id
	 * @param attributes
	 * @return 标签管理页面
	 */
	@GetMapping("/tags/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		tagService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/tags";
	}

}
