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

	void deleteByBlogId(Long blogId);

	void save(Comment comment);

	List<CommentExtDTO> listParentCommentByBlogId(Long blogId);

	List<CommentExtDTO> listChildCommentByParentId(Long parentId);

	void incReplyCntById(Long id);

	void incLikeCntByCommentId(Long commentId);

	void incDownCntByCommentId(Long commentId);

	
}
