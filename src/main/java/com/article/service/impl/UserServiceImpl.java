package com.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.article.entity.User;
import com.article.mapper.UserMapper;
import com.article.service.UserService;

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
	public void updateTokenById(String token, Long id) {
		userMapper.updateTokenById(token,id);
	}

	@Override
	public void updateLastLoginTimeById(Long id) {
		userMapper.updateLastLoginTimeById(id);
	}

}
