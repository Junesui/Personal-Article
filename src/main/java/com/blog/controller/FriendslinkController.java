package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.entity.Friendslink;
import com.blog.service.FriendslinkService;

/**
 * 友人链控制器
 * @author June
 * @date 2020/06/21
 * @version V1.0
 */
@Controller
public class FriendslinkController {

	@Autowired
	private FriendslinkService friendslinkService;
	
	/**
	 * 跳转到友人链页面
	 * @return 友人链页面
	 */
	@GetMapping("/friendslink")
	public String toFriendslink(Model model) {
		List<Friendslink> friendslinks = friendslinkService.listShow();
		model.addAttribute("friendslinks", friendslinks);
		return "friendslink";
	}
	
	@PostMapping("/addFriendslink")
	public String post(Friendslink friendslink,RedirectAttributes redirectAttributes) {
		friendslinkService.save(friendslink);
		redirectAttributes.addFlashAttribute("message", "提交成功，等待管理员审核...");
		return "redirect:/friendslink";
	}
	
	
	
	
	
	
	
	
	
	
}
