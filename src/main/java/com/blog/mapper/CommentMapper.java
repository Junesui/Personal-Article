package com.blog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.dto.CommentExtDTO;
import com.blog.entity.Comment;

/**
 * 博客评论接口
 * @author June
 * @date 2020/05/14
 * @version V1.0
 */
@Mapper
public interface CommentMapper {

	public void deleteByBlogId(Long blogId);

	public void save(Comment comment);

	public List<CommentExtDTO> listParentCommentByBlogId(Long blogId);

	public List<CommentExtDTO> listChildCommentByParentId(Long parentId);

	public void incReplyCntById(Long id);

	public void incLikeCntByCommentId(Long commentId);

	public void incDownCntByCommentId(Long commentId);

	
}
