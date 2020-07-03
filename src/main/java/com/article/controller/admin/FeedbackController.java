package com.article.controller.admin;

import java.util.ArrayList;
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

import com.article.entity.Feedback;
import com.article.service.FeedbackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 反馈建议后台管理
 * @author June
 * @date 2020/07/02
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;
	
	//反馈建议后台管理每页展示的数量
	@Value("${admin.feedbackSize}")
	private Integer feedbackSize;
	
	
	/**
	 * 跳转到后台反馈管理页面
	 * @param page 页码
	 * @param size 每页展示的数量
	 * @param model
	 * @return 反馈管理页面
	 */
	@GetMapping("/feedback")
	public String toFeedback(@RequestParam(name = "page", defaultValue = "1") Integer page,
					         @RequestParam(name = "size", defaultValue = "10") Integer size, 
					         Model model) {
		size = feedbackSize;
		//开始分页
		PageHelper.startPage(page, size);
		List<Feedback> feedbacks = feedbackService.list();
		
		PageInfo<Feedback> pageInfo = new PageInfo<>(feedbacks);
		model.addAttribute("pageInfo", pageInfo);
		return "/admin/feedback";
	}
	
	/**
	 * 通过id设置反馈为已修复状态
	 * @param id 反馈id
	 * @param attributes
	 * @return 反馈管理页面
	 */
	@GetMapping("/feedback/fix/{id}")
	public String fixById(@PathVariable("id") Long id,RedirectAttributes attributes) {
		feedbackService.fixById(id);
		attributes.addFlashAttribute("message", "成功设为已修复状态");
		return "redirect:/1120/feedback";
	}
	
	/**
	 * 通过id设置反馈为未修复状态
	 * @param id 反馈id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/feedback/nofix/{id}")
	public String nofixById(@PathVariable("id") Long id,RedirectAttributes attributes) {
		feedbackService.nofixById(id);
		attributes.addFlashAttribute("message", "成功设为未修复状态");
		return "redirect:/1120/feedback";
	}
	
	/**
	 * 通过id删除反馈
	 * @param id 反馈id
	 * @param attributes
	 * @return
	 */
	@GetMapping("/feedback/delete/{id}")
	public String deleteById(@PathVariable("id") Long id,RedirectAttributes attributes) {
		feedbackService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/feedback";
	}
	
	/**
	 * 通过条件搜索反馈
	 * @param page 页码
	 * @param size 每页展示数据的大小
	 * @param isFixed 是否修复
	 * @param model
	 * @return 反馈管理页面
	 */
	@PostMapping("/feedback/search")
	public String search(@RequestParam(name = "page", defaultValue = "1") Integer page,
			             @RequestParam(name = "size", defaultValue = "10") Integer size, 
			             Boolean isFixed, Model model) {
		size = feedbackSize;
		List<Feedback> feedbacks = new ArrayList<Feedback>();
		//分页
		PageHelper.startPage(page, size);
		//不加搜索条件时候，搜索所有的友人链
		if (isFixed == null) {
			feedbacks = feedbackService.list();
		}else {
			feedbacks = feedbackService.listBySearch(isFixed);
		}
		
		PageInfo<Feedback> pageInfo = new PageInfo<>(feedbacks);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/feedback :: feedbackList";
	}
	
	
	
	
	
	
	
}
