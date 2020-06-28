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

import com.article.entity.Friendslink;
import com.article.service.FriendslinkService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 友人链后台管理控制器
 * @author June
 * @date 2020/06/24
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class FriendslinkController {

	@Autowired
	private FriendslinkService friendslinkService;
	
	//友人链后台管理每页展示的友链数量
	@Value("${admin.friendslinkSize}")
	private Integer friendslinkSize;
	
	
	/**
	 * 跳转到友人链管理页面
	 * @param page 页码
	 * @param size 每页展示的数量
	 * @param model
	 * @return 友人链管理页面
	 */
	@GetMapping("/friendslink")
	public String toFriendslink(@RequestParam(name = "page", defaultValue = "1") Integer page,
				 		        @RequestParam(name = "size", defaultValue = "10") Integer size, 
				 		        Model model) {
		size = friendslinkSize;
		PageHelper.startPage(page, size);
		List<Friendslink> friendslinks = friendslinkService.list();
		
		PageInfo<Friendslink> pageInfo = new PageInfo<>(friendslinks);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/friendslink";
	}
	
	/**
	 * 通过id设置友人链为展示状态
	 * @param id 友人链id
	 * @param model
	 * @return 友人链管理页面
	 */
	@GetMapping("/friendslink/show/{id}")
	public String showById(@PathVariable("id") Integer id,RedirectAttributes attributes) {
		friendslinkService.showById(id);
		attributes.addFlashAttribute("message", "成功设为展示状态");
		return "redirect:/1120/friendslink";
	}
	
	/**
	 * 通过id设置友人链为不展示状态
	 * @param id 友人链id
	 * @param model
	 * @return 友人链管理页面
	 */
	@GetMapping("/friendslink/hide/{id}")
	public String hideById(@PathVariable("id") Integer id,RedirectAttributes attributes) {
		friendslinkService.hideById(id);
		attributes.addFlashAttribute("message", "成功设为不展示状态");
		return "redirect:/1120/friendslink";
	}
	
	/**
	 * 通过id删除友人链
	 * @param id 友人链id
	 * @param attributes
	 * @return 友人链管理页面
	 */
	@GetMapping("/friendslink/delete/{id}")
	public String deleteById(@PathVariable("id") Integer id,RedirectAttributes attributes) {
		friendslinkService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/friendslink";
	}
	
	@PostMapping("/friendslink/search")
	public String search(@RequestParam(name = "page", defaultValue = "1") Integer page,
			             @RequestParam(name = "size", defaultValue = "10") Integer size, 
			             Boolean isShow, Model model) {
		size = friendslinkSize;
		List<Friendslink> dto = new ArrayList<Friendslink>();
		//分页
		PageHelper.startPage(page, size);
		//不加搜索条件时候，搜索所有的友人链
		if (isShow == null) {
			dto = friendslinkService.list();
		}else {
			dto = friendslinkService.listBySearch(isShow);
		}
		
		PageInfo<Friendslink> pageInfo = new PageInfo<>(dto);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/friendslink :: friendslinkList";
	}
	
}
