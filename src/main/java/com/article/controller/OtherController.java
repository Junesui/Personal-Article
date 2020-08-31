package com.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author June
 * @date 2020/08/31
 * @version V1.0
 */
@Controller
public class OtherController {

	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
	
}
