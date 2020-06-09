package com.blog.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dto.CommentExtDTO;
import com.blog.entity.Comment;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;

/**
 * 博客评论实现类
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private BlogMapper blogMapper;

	//存放迭代找出的所有子代的集合
	private List<Comment> replys = new ArrayList<>();
 
	
	@Override
	public void deleteByBlogId(Long blogId) {
		commentMapper.deleteByBlogId(blogId);
	}

	@Transactional
	@Override
	public void save(Comment comment) {
		//一级评论的父id设置为空
		if (comment.getParentId() == -1) {
			comment.setParentId(null);
			blogMapper.incCommentCntById(comment.getBlogId());
		}
		comment.setCreateTime(new Date(System.currentTimeMillis()));
		commentMapper.save(comment);
	}

	@Override
	public List<CommentExtDTO> listParentCommentByBlogId(Long blogId) {
		List<CommentExtDTO> parentComments = commentMapper.listParentCommentByBlogId(blogId);
		for (CommentExtDTO pc : parentComments) {
			//通过父id列出子评论
			List<CommentExtDTO> childComments = commentMapper.listChildCommentByParentId(pc.getId());
			combineChildren(childComments, pc.getNickname());
			pc.setReplyComments(replys);
			replys = new ArrayList<>();
		}
		return parentComments;
	}

	/**
	 * 遍历并迭代
	 * @param comments
	 * @param pNickname
	 */
	private void combineChildren(List<CommentExtDTO> comments, String pNickname) {
		if (comments.size() > 0) {
			for (CommentExtDTO comment : comments) {
				comment.setParentNickname(pNickname);
				replys.add(comment);
				recursively(comment, comment.getNickname());
			}
		}
	}

	/**
	 * 递归迭代，剥洋葱
	 * @param comment 被迭代的对象
	 * @param pNickname 夫评论昵称
	 */
	private void recursively(CommentExtDTO comment, String pNickname) {

		List<CommentExtDTO> replyComments = commentMapper.listChildCommentByParentId(comment.getId());

		if (replyComments.size() > 0) {
			for (CommentExtDTO reComment : replyComments) {
				reComment.setParentNickname(pNickname);
				replys.add(reComment);
				recursively(reComment, reComment.getNickname());
			}
		}

	}

}
