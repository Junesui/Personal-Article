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

}
