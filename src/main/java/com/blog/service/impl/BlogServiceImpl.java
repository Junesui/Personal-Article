package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dto.BlogQueryDTO;
import com.blog.dto.BlogTypeTagDTO;
import com.blog.entity.Blog;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.TagMapper;
import com.blog.service.BlogService;
import com.blog.util.StringToListUtils;

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

	
	@Override
	public List<BlogTypeTagDTO> listBlogAndTypeName() {
		return blogMapper.listBlogAndTypeName();
	}

	@Transactional
	@Override
	public int saveOrUpdate(BlogTypeTagDTO dto) {
		
		//字符串标签ids转换为列表
		List<Long> tagIdList = StringToListUtils.convert(dto.getTagIds());

		Long blogId = dto.getId();
		if (blogId == null) {
			//插入操作
			dto.setCreateTime(new Date(System.currentTimeMillis()));
			dto.setUpdateTime(new Date(System.currentTimeMillis()));
			//插入博客
			int blogSaveRst = blogMapper.save(dto);
			//插入blog_tag中间表
			for (Long tagId : tagIdList) {
				tagMapper.saveBlogAndTag(dto.getId(),tagId);
			}
			//插入成功
			if ((blogSaveRst==1)) {
				return 1;
			}
		} else {
			//更新操作
			Blog b = blogMapper.findById(blogId);
			BeanUtils.copyProperties(dto, b);
			b.setUpdateTime(new Date(System.currentTimeMillis()));
			//更新博客
			int blogUpdateRst = blogMapper.update(b);
			//删除该博客的blog_tag中间表数据，重新插入blog_tag中间表
			tagMapper.deleteByBlogId(blogId);
			for (Long tagId : tagIdList) {
				tagMapper.saveBlogAndTag(blogId, tagId);
			}
			//更新成功
			if ((blogUpdateRst == 1)) {
				return 1;
			}
		}
		return 0;
	}

	@Override
	public List<BlogTypeTagDTO> listBySearch(BlogQueryDTO blogQueryDTO) {
		
		return blogMapper.listBySearch(blogQueryDTO);
	}

}
