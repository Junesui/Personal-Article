package com.article.controller.admin;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import com.article.entity.Message;
import com.article.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 留言管理控制器
 * @author June
 * @date 2020/07/22
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	//留言后台管理每页展示的留言数量
	@Value("${admin.MessageSize}")
	private Integer MessageSize;
	
	
	/**
	 * 跳转到留言管理页面
	 * @param page 页码
	 * @param size 每页展示留言的数量
	 * @param model
	 * @return 留言管理页面
	 */
	@GetMapping("/message")
	public String toMessage(@RequestParam(name = "page", defaultValue = "1") Integer page,
 		      				@RequestParam(name = "size", defaultValue = "10") Integer size,
 		      				Model model) {
		size=MessageSize;
		//分页
		PageHelper.startPage(page, size);
		List<Message> messages = messageService.list();
		
		PageInfo<Message> pageInfo = new PageInfo<>(messages);
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/message";
	}
	
	/**
	 * 后台搜索留言
	 * @param page 页码
	 * @param size 每页显示的留言数量
	 * @param content 搜索内容
	 * @param model
	 * @return 留言显示片段
	 */
	@PostMapping("/message/search")
	public String search(@RequestParam(name = "page", defaultValue = "1") Integer page,
			             @RequestParam(name = "size", defaultValue = "10") Integer size, 
			             String content, Model model) {
		size = MessageSize;
		if (StringUtils.isBlank(content)) {
			content = "%";
		}
		//分页
		PageHelper.startPage(page, size);
		List<Message> messages = messageService.listBySearch(content);
		PageInfo<Message> pageInfo = new PageInfo<>(messages);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/message :: messageList";
	}
	
	/**
	 * 通过id删除留言
	 * @param id 留言id
	 * @param attributes
	 * @return 留言管理页面
	 */
	@GetMapping("/message/delete/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		messageService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/message";
	}
}
