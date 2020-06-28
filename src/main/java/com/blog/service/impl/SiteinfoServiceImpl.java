package com.blog.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.blog.dto.SiteinfoExtDTO;
import com.blog.entity.Siteinfo;
import com.blog.mapper.BlogMapper;
import com.blog.mapper.CommentMapper;
import com.blog.mapper.FriendslinkMapper;
import com.blog.mapper.MessageMapper;
import com.blog.mapper.OnewordMapper;
import com.blog.mapper.SiteinfoMapper;
import com.blog.mapper.TagMapper;
import com.blog.mapper.ToolMapper;
import com.blog.mapper.TypeMapper;
import com.blog.service.SiteinfoService;

/**
 * 网站信息服务层实现类
 * @author June
 * @date 2020/06/27
 * @version V1.0
 */
@Service
public class SiteinfoServiceImpl implements SiteinfoService{

	@Autowired
	private SiteinfoMapper siteinfoMapper;
	@Autowired
	private BlogMapper blogMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private TagMapper tagMapper;
	@Autowired
	private FriendslinkMapper friendslinkMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private OnewordMapper onewordMapper;
	@Autowired
	private ToolMapper toolMapper;
	
	@Value("${admin.siteBeginDate}")
	private String siteBeginDate;
	
	
	@Override
	public SiteinfoExtDTO querySiteinfo() {
		SiteinfoExtDTO dto = new SiteinfoExtDTO();
		
		Integer articleCount = blogMapper.countShow();
		Integer commentCount = commentMapper.count();
		Integer typeCount = typeMapper.countAll();
		Integer tagCount = tagMapper.countAll();
		Integer friendslinkCount = friendslinkMapper.countShow();
		Integer messageCount = messageMapper.count();
		Integer onewordCount = onewordMapper.countShow();
		Integer toolCount = toolMapper.count();
		Long viewCount = siteinfoMapper.findViewCount();
		try {
			Long runTime = (new Date().getTime() - new SimpleDateFormat("yyyy-MM-dd").parse(siteBeginDate).getTime())/1000/60/60/24;
			dto.setRunTime(runTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dto.setArticleCount(articleCount);
		dto.setCommentCount(commentCount);
		dto.setTypeCount(typeCount);
		dto.setTagCount(tagCount);
		dto.setFriendslinkCount(friendslinkCount);
		dto.setMessageCount(messageCount);
		dto.setOnewordCount(onewordCount);
		dto.setToolCount(toolCount);
		dto.setViewCount(viewCount);
		return dto;
	}

	@Override
	public void initUpdate() {
		siteinfoMapper.initUpdate();	
	}

	@Override
	public Siteinfo find() {
		return siteinfoMapper.find();
	}

	@Override
	public void incViewCnt(Long viewCntWrite) {
		siteinfoMapper.incViewCnt(viewCntWrite);
	}

	@Override
	public void update() {
		siteinfoMapper.update();
	}

}
