package com.article.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.article.entity.Friendslink;
import com.article.mapper.FriendslinkMapper;
import com.article.service.FriendslinkService;

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

	@Override
	public void viewFriendslinkById(Integer id) {
		friendslinkMapper.viewFriendslinkById(id);
	}

	@Override
	public Friendslink findByWebsiteUrl(String websiteUrl) {
		return friendslinkMapper.findByWebsiteUrl(websiteUrl);
	}

	@Override
	public List<Friendslink> list() {
		return friendslinkMapper.list();
	}

	@Override
	public void showById(Integer id) {
		friendslinkMapper.showById(id);
	}

	@Override
	public void hideById(Integer id) {
		friendslinkMapper.hideById(id);
	}

	@Override
	public void deleteById(Integer id) {
		friendslinkMapper.deleteById(id);
	}

	@Override
	public List<Friendslink> listBySearch(Boolean isShow) {
		return friendslinkMapper.listBySearch(isShow);
	}

}
