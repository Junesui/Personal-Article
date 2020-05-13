package com.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import com.blog.util.MD5Utils;

/**
 * 用户service实现类
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User findByToken(String token) {
		return userMapper.findByToken(token);
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		User user = userMapper.findByUsernameAndPassword(username, MD5Utils.code(password));
		return user;
	}

	@Override
	public void updateTokenById(String token, Long id) {
		userMapper.updateTokenById(token,id);
	}

}
