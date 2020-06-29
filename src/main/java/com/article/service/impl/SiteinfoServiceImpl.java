package com.article.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.article.dto.SiteinfoExtDTO;
import com.article.entity.Siteinfo;
import com.article.mapper.ArticleMapper;
import com.article.mapper.CommentMapper;
import com.article.mapper.FriendslinkMapper;
import com.article.mapper.MessageMapper;
import com.article.mapper.OnewordMapper;
import com.article.mapper.SiteinfoMapper;
import com.article.mapper.TagMapper;
import com.article.mapper.ToolMapper;
import com.article.mapper.TypeMapper;
import com.article.service.SiteinfoService;

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
	private ArticleMapper articleMapper;
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
		
		Integer articleCount = articleMapper.countShow();
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
	public void save() {
		siteinfoMapper.save();	
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
