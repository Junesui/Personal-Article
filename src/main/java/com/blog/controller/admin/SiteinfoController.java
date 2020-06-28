package com.blog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.dto.SiteinfoExtDTO;
import com.blog.service.SiteinfoService;

/**
 * 网站信息控制器
 * @author June
 * @date 2020/06/26
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class SiteinfoController {

	@Autowired
	private SiteinfoService siteinfoService;
	
	
	/**
	 * 查询网站信息
	 * @param model
	 * @return 网站信息后台管理页面
	 */
	@GetMapping("/siteinfo")
	public String querySiteinfo(Model model) {
		SiteinfoExtDTO siteinfoExtDTO = siteinfoService.querySiteinfo();
		model.addAttribute("siteinfoExtDTO", siteinfoExtDTO);
		return "admin/siteinfo";
	}
	
}
