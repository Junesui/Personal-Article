package com.blog.service;

import java.util.List;

import com.blog.entity.Friendslink;

/**
 * 友人链服务层接口
 * @author June
 * @date 2020/06/22
 * @version V1.0
 */
public interface FriendslinkService {

	// 列出要展示的友人链信息
	List<Friendslink> listShow();

	//添加友人链
	void save(Friendslink friendslink);

	//访问友人链，更新友人链相关数据
	void viewFriendslinkById(Integer id);

	//通过网站地址查找友人链
	Friendslink findByWebsiteUrl(String websiteUrl);

}
