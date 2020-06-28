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

import com.article.entity.Tool;
import com.article.service.ToolService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 工具后台管理控制器
 * @author June
 * @date 2020/06/25
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class ToolController {

	@Autowired
	private ToolService toolService;

	//文章首页展示的文章数量
	@Value("${admin.toolSize}")
	private Integer toolSize;

	
	/**
	 * 跳转到工具后台管理页
	 * @param page 页码
	 * @param size 每页展示的数量
	 * @param model
	 * @return 工具后台管理页
	 */
	@GetMapping("/tool")
	public String toTool(@RequestParam(name = "page", defaultValue = "1") Integer page,
			             @RequestParam(name = "size", defaultValue = "10") Integer size, 
			             Model model) {
		size = toolSize;
		//分页
		PageHelper.startPage(page, size);
		List<Tool> tools = toolService.list();
		
		PageInfo<Tool> pageInfo = new PageInfo<>(tools);
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/tools";
	}
	
	/**
	 * 跳转到工具添加页面
	 * @param model
	 * @return 工具添加页面
	 */
	@GetMapping("/tool/addPage")
	public String toAdd(Model model) {
		model.addAttribute("tool", new Tool());
		return "admin/tool-release";
	}
	
	/**
	 * 跳转到工具编辑页面
	 * @param id 工具id
	 * @param model
	 * @return 工具编辑页面
	 */
	@GetMapping("/tool/edit/{id}")
	public String toEdit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("tool", toolService.findById(id));
		return "admin/tool-release";
	}
	
	/**
	 * 新增工具
	 * @param tool 工具
	 * @param attributes
	 * @param model
	 * @return 后台工具管理页面
	 */
	@PostMapping("/tool")
	public String post(Tool tool, RedirectAttributes attributes, Model model) {
		//名称是否重复验证
		if (toolService.findByName(tool.getName()) != null) {
			model.addAttribute("fieldError", "工具名称已经存在");
			return "admin/tool-release";
		}
		//链接是否重复验证
		if (toolService.findByUrl(tool.getUrl()) != null) {
			model.addAttribute("fieldError", "工具链接已经存在");
			return "admin/tool-release";
		}

		toolService.save(tool);
		attributes.addFlashAttribute("message", "新增成功");
		return "redirect:/1120/tool";
	}

	/**
	 * 编辑工具
	 * @param tool 工具
	 * @param id 工具id
	 * @param attributes
	 * @param model
	 * @return 
	 */
	@PostMapping("/tool/{id}")
	public String editPost(Tool tool, @PathVariable("id") Long id, RedirectAttributes attributes, Model model) {
		toolService.update(tool);
		attributes.addFlashAttribute("message", "更新成功");
		return "redirect:/1120/tool";
	}
	
	/**
	 * 删除工具
	 * @param id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/tool/delete/{id}")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes attributes) {
		toolService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/tool";
	}
	
}
