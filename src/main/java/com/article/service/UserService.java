package com.article.service;

import com.article.entity.User;

/**
 * 用户service接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public interface UserService {

	//通过token查找用户
	User findByToken(String token);

	//通过id更新token
	void updateTokenById(String token, Long id);

	//通过用户id更新最有一次登陆时间为现在的时间
	void updateLastLoginTimeById(Long id);

}
