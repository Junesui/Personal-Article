package com.article.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.article.entity.User;

import lombok.Data;

/**
 * 
 * @author June
 * @date 2020/08/29
 * @version V1.0
 */
@Data
public class UserDTO extends User implements UserDetails{

	//这四个暂且都给默认值为true
	private boolean isAccountNonExpired=true;
	private boolean isAccountNonLocked=true;
	private boolean isCredentialsNonExpired=true;
	private boolean isEnabled=true;
	
	//存储权限的集合
	private List<GrantedAuthority> authorities;
	
}
