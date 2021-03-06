package com.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.article.entity.Friendslink;

/**
 * 友人链mapper接口
 * @author June
 * @date 2020/06/22
 * @version V1.0
 */
@Mapper
public interface FriendslinkMapper {

	List<Friendslink> listShow();

	void save(Friendslink friendslink);

	void viewFriendslinkById(Integer id);

	Friendslink findByWebsiteUrl(String websiteUrl);

	List<Friendslink> list();

	void showById(Integer id);

	void hideById(Integer id);

	void deleteById(Integer id);

	List<Friendslink> listBySearch(Boolean isShow);

	Integer countShow();

	
}
