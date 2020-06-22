package com.blog.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Friendslink;
import com.blog.mapper.FriendslinkMapper;
import com.blog.service.FriendslinkService;

/**
 * 友人链服务层实现类
 * @author June
 * @date 2020/06/22
 * @version V1.0
 */
@Service
public class FriendslinkServiceImpl implements FriendslinkService{

	@Autowired
	private FriendslinkMapper friendslinkMapper;
	
	@Override
	public List<Friendslink> listShow() {
		return friendslinkMapper.listShow();
	}

	@Override
	public void save(Friendslink friendslink) {
		friendslink.setCreateTime(new Date(System.currentTimeMillis()));
		friendslink.setUpdateTime(new Date(System.currentTimeMillis()));
		friendslink.setIsShow(false);
		friendslink.setViewCount(0L);
		friendslinkMapper.save(friendslink);
	}

}
