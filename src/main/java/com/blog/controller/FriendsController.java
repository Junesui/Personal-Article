package com.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 友人链控制器
 * @author June
 * @date 2020/06/21
 * @version V1.0
 */
@Controller
public class FriendsController {

	@GetMapping("/friends")
	public String toFriends() {
		return "friends";
	}
}
