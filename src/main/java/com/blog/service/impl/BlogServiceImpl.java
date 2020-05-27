package com.blog.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dto.BlogQueryDTO;
import com.blog.dto.BlogTypeTagDTO;
import com.blog.entity.Blog;
import com.blog.exception.CustomizeErrorCode;
import com.blog.exception.CustomizeException;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.TagMapper;
import com.blog.service.BlogService;
import com.blog.service.CommentService;
import com.blog.util.MarkdownUtils;
import com.blog.util.StringAndListConvertUtils;

/**
 * 博客service实现类
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private CommentService commentService;

	//博客访问数量
	private Integer viewCnt = 0;
	//博客访问数量达到viewCntWrite次，再一次性写入数据库
	@Value("${blog.viewCntWrite}")
	private Long viewCntWrite;

	@Override
	public List<BlogTypeTagDTO> listBlogAndType() {
		return blogMapper.listBlogAndType();
	}

	@Transactional
	@Override
	public void saveOrUpdate(BlogTypeTagDTO dto) {

		//字符串标签ids转换为列表
		List<Long> tagIdList = StringAndListConvertUtils.toList(dto.getTagIds());

		Long blogId = dto.getId();
		if (blogId == null) {
			//插入操作
			dto.setCreateTime(new Date(System.currentTimeMillis()));
			dto.setUpdateTime(new Date(System.currentTimeMillis()));
			dto.setViewCount(0);
			//插入博客
			blogMapper.save(dto);
			//插入blog_tag中间表
			for (Long tagId : tagIdList) {
				tagMapper.saveBlogAndTag(dto.getId(), tagId);
			}
		} else {
			//更新操作
			Blog b = blogMapper.findById(blogId);
			BeanUtils.copyProperties(dto, b);
			b.setUpdateTime(new Date(System.currentTimeMillis()));
			//更新博客
			blogMapper.update(b);
			//删除该博客的blog_tag中间表数据，重新插入blog_tag中间表
			tagMapper.deleteByBlogId(blogId);
			for (Long tagId : tagIdList) {
				tagMapper.saveBlogAndTag(blogId, tagId);
			}
		}
	}

	@Override
	public List<BlogTypeTagDTO> listBySearch(BlogQueryDTO blogQueryDTO) {

		return blogMapper.listBySearch(blogQueryDTO);
	}

	@Override
	public BlogTypeTagDTO findBlogAndTypeById(Long id) {
		return blogMapper.findBlogAndTypeById(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {

		//删除中间表
		tagMapper.deleteByBlogId(id);
		//删除评论
		commentService.deleteByBlogId(id);
		//删除博客
		blogMapper.deleteById(id);

	}

	@Override
	public List<BlogTypeTagDTO> listTopBlog() {
		return blogMapper.listTopBlog();
	}

	@Override
	public List<Blog> listTopRecommendBlog(Integer topRecommendNum) {
		return blogMapper.listTopRecommendBlog(topRecommendNum);
	}

	@Override
	public BlogTypeTagDTO findAndConvertById(Long id) {
		BlogTypeTagDTO blogTypeTagDTO = blogMapper.findBlogDetailById(id);
		if (blogTypeTagDTO == null) {
			throw new CustomizeException(CustomizeErrorCode.ARTICLE_NOT_FOUND);
		}
		String content = blogTypeTagDTO.getContent();
		blogTypeTagDTO.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
		return blogTypeTagDTO;
	}

	@Override
	public void incViewCntById(Long id) {
		viewCnt = viewCnt + 1;
		//访问数量增加7次后，再一次性写入数据库
		if (viewCnt == 7 || viewCnt > 7) {
			viewCnt = 0;
			blogMapper.incViewCntById(viewCntWrite, id);
		}
	}

	@Override
	public List<BlogTypeTagDTO> listTypeBlogByTypeId(Long id) {
		return blogMapper.listTypeBlogByTypeId(id);
	}

	@Override
	public List<BlogTypeTagDTO> listTagBlogByTagId(Long id) {
		return blogMapper.listTagBlogByTagId(id);
	}

	@Override
	public Long count() {
		return blogMapper.count();
	}

	@Override
	public Map<String, List<Blog>> archiveBlog() {
		Map<String, List<Blog>> map = new LinkedHashMap<String, List<Blog>>();
		List<String> years = blogMapper.listGroupYear();
		for (String year : years) {
			List<Blog> blogs = blogMapper.listByYear(year);
			map.put(year, blogs);
		}
		return map;
	}

	@Override
	public List<BlogTypeTagDTO> listByQuery(String query) {
		return blogMapper.listByQuery(query);
	}

}
