package com.article.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.article.dto.CommentExtDTO;
import com.article.entity.Comment;
import com.article.mapper.ArticleMapper;
import com.article.mapper.CommentMapper;
import com.article.service.CommentService;

/**
 * 文章评论实现类
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;

	//存放迭代找出的所有子代的集合
	private List<Comment> replys = new ArrayList<>();
 
	
	@Override
	public void deleteByArticleId(Long articleId) {
		commentMapper.deleteByArticleId(articleId);
	}

	@Transactional
	@Override
	public void save(Comment comment) {
		//一级评论的父id设置为空
		if (comment.getParentId() == -1) {
			comment.setParentId(null);
			articleMapper.incCommentCntById(comment.getArticleId());
		}else {
			commentMapper.incReplyCntById(comment.getParentId());
		}
		comment.setCreateTime(new Date(System.currentTimeMillis()));
		comment.setLikeCount(0);
		comment.setDownCount(0);
		comment.setReplyCount(0);
		
		commentMapper.save(comment);
	}

	@Override
	public List<CommentExtDTO> listParentCommentByArticleId(Long articleId) {
		List<CommentExtDTO> parentComments = commentMapper.listParentCommentByArticleId(articleId);
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
	 * @param pNickname 父评论昵称
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

	@Override
	public void incLikeCntByCommentId(Long commentId) {
		commentMapper.incLikeCntByCommentId(commentId);
	}

	@Override
	public void incDownCntByCommentId(Long commentId) {
		commentMapper.incDownCntByCommentId(commentId);
	}

}
