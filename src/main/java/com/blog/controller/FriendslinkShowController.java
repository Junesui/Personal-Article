package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.dto.MessageExtDTO;
import com.blog.entity.Friendslink;
import com.blog.entity.Message;
import com.blog.entity.User;
import com.blog.service.FriendslinkService;
import com.blog.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 友人链控制器
 * @author June
 * @date 2020/06/21
 * @version V1.0
 */
@Controller
public class FriendslinkShowController {

	@Autowired
	private FriendslinkService friendslinkService;
	@Autowired
	private MessageService messageService;
	
	//游客留言用到的头像
	@Value("${message.avatar}")
	private String avatar;
	//友人链页面单页展示的数量
	@Value("${friendslink.linkSize}")
	private Integer linkSize;
	
	/**
	 * 跳转到友人链页面
	 * @return 友人链页面
	 */
	@GetMapping("/friendslink")
	public String toFriendslink(@RequestParam(name = "page", defaultValue = "1") Integer page,
            					@RequestParam(name = "size", defaultValue = "11") Integer size, 
            					Model model) {
		size = linkSize;
		//分页
		PageHelper.startPage(page, size);
		List<Friendslink> friendslinks = friendslinkService.listShow();
		
		List<MessageExtDTO> messages = messageService.listMessage();
		PageInfo<Friendslink> pageInfo = new PageInfo<>(friendslinks);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("page", page);
		model.addAttribute("messages", messages);
		return "friendslink";
	}
	
	/**
	 * 保存友人链
	 * @param friendslink 友人链
	 * @param redirectAttributes
	 * @return 友人链页面
	 */
	@PostMapping("/addFriendslink")
	public String postFriendslink(Friendslink friendslink,RedirectAttributes redirectAttributes) {
		String websiteName = friendslink.getWebsiteName();
		String websiteDescription = friendslink.getWebsiteDescription();
		String websiteUrl = friendslink.getWebsiteUrl();
		//判断网站地址是否重复
		Friendslink f = friendslinkService.findByWebsiteUrl(StringUtils.trim(websiteUrl));
		if (f != null) {
			redirectAttributes.addFlashAttribute("message", "网站地址已申请或添加，请勿重复提交");
			return "redirect:/friendslink";
		}
		//字段长度校验
		if (websiteName.length() > 16 || websiteDescription.length() > 28) {
			redirectAttributes.addFlashAttribute("message", "提交失败：「网站名称」或「网站简介」的字数超过了限制");
			return "redirect:/friendslink";
		}
		friendslinkService.save(friendslink);
		redirectAttributes.addFlashAttribute("message", "提交成功，等待管理员审核...");
		return "redirect:/friendslink";
	}
	
	/**
	 * 点击友人链后，更新友人链信息
	 * @param id 友人链id
	 */
	@ResponseBody
	@GetMapping("/viewFriendslink")
	public String viewFriendslinkById(Integer id) {
		friendslinkService.viewFriendslinkById(id);
		return "";
	}
	
	/**
	 * 获取并展示留言
	 * @param model
	 * @return
	 */
	@GetMapping("/messages")
	public String messages(Model model) {
		List<MessageExtDTO> messages = messageService.listMessage();
		model.addAttribute("messages", messages);
		return "friendslink :: messageList";
	}
	
	/**
	 * 提交留言
	 * @param message 留言
	 * @param session
	 * @return
	 */
	@PostMapping("/postMessages")
	public String postMessage(Message message,HttpSession session,Model model) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			//设置博主头像
			message.setAvatar(user.getAvatar());
			message.setIsManager(true);
		}else {
			//设置游客头像
			message.setAvatar(avatar);
			message.setIsManager(false);
		}
		messageService.save(message);
		return "redirect:/messages";
	}
	
}
