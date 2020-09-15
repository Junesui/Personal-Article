package com.article.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.article.dto.UserDTO;
import com.article.entity.Permission;
import com.article.mapper.UserMapper;

/**
 * 
 * @author June
 * @date 2020/08/26
 * @version V1.0
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//定义存储权限的列表
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		//通过用户名找到用户
		UserDTO userDTO = userMapper.findByUsername(username);
		if (userDTO != null) {
			//通过用户名找到权限
			List<Permission> perms = userMapper.listPermissionByUserId(userDTO.getId());
			for (Permission p : perms) {
				authorities.add(new SimpleGrantedAuthority(p.getName()));
			}
			userDTO.setAuthorities(authorities);
		}
		return userDTO;
	}
}
