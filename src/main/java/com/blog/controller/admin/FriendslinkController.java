package com.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.service.FriendslinkService;

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
	
	@GetMapping("/friendslink")
	public String toFriendslink() {
		
		return "admin/friendslink";
	}
	
	
	
}
