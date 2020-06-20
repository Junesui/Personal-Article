package com.blog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.blog.dto.CommentExtDTO;
import com.blog.entity.Comment;
import com.blog.entity.User;
import com.blog.service.CommentService;

/**
 * 评论控制器
 * @author June
 * @date 2020/05/18
 * @version V1.0
 */
@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Value("${comment.avatar}")
	private String avatar;
	
	
	/**
	 * 获取并展示评论
	 * @param blogId
	 * @param model
	 * @return 博客的评论列表片段
	 */
	@GetMapping("/comments/{blogId}")
	public String comments(@PathVariable Long blogId,Model model) {
		List<CommentExtDTO> comments = commentService.listParentCommentByBlogId(blogId);
		model.addAttribute("comments", comments);
		return "blog :: commentList";
	}
	
	/**
	 * 提交评论
	 * @param comment
	 * @param session
	 * @return 评论展示方法
	 */
	@PostMapping("/comments")
	public String post(Comment comment,HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			//设置博主头像
			comment.setAvatar(user.getAvatar());
			comment.setIsManager(true);
		}else {
			//设置游客头像
			comment.setAvatar(avatar);
			comment.setIsManager(false);
		}
		commentService.save(comment);
		return "redirect:/comments/" + comment.getBlogId();
	}
	
	/**
	 * 根据评论id增加评论赞的数量
	 * @param commentId 评论id
	 * @return 博客的评论列表片段
	 */
	@GetMapping("/incLikeCnt")
	public String incLikeCntByCommentId(Long commentId) {
		commentService.incLikeCntByCommentId(commentId);
		return "forward:/comments/" + commentId;
	}
	
	
}
