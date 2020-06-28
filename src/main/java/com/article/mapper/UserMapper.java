package com.article.mapper;

import org.apache.ibatis.annotations.Mapper;

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

	User findByUsernameAndPassword(String username, String password);

	void updateTokenById(String token, Long id);

}
