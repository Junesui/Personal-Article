package com.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.blog.entity.Siteinfo;

/**
 * 网站信息数据库层接口
 * @author June
 * @date 2020/06/27
 * @version V1.0
 */
@Mapper
public interface SiteinfoMapper {

	Long findViewCount();

	void initUpdate();

	Siteinfo find();

	void incViewCnt(Long viewCntWrite);

	void update();

}
