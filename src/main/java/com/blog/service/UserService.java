package com.blog.service;

import com.blog.entity.User;

/**
 * 用户service接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public interface UserService {

	//通过token查找用户
	User findByToken(String token);

	//通过用户名和密码查找用户
	User findByUsernameAndPassword(String username, String password);

	//通过id更新token
	void updateTokenById(String token, Long id);

}
