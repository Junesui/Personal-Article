package com.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.article.dto.UserDTO;
import com.article.entity.Permission;
import com.article.entity.User;

/**
 * 用户mapper接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Mapper
public interface UserMapper {

	User findByToken(String token);

	void updateTokenById(String token, Long id);

	void updateLastLoginTimeById(Long id);

	UserDTO findByUsername(String username);

	List<Permission> listPermissionByUserId(Long id);

}
