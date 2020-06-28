package com.blog.service;

import com.blog.dto.SiteinfoExtDTO;
import com.blog.entity.Siteinfo;

/**
 * 网站信息服务层接口
 * @author June
 * @date 2020/06/27
 * @version V1.0
 */
public interface SiteinfoService {

	//查询网站所有信息
	SiteinfoExtDTO querySiteinfo();

	//设置网站总访问数初始化为0
	void initUpdate();

	//获取网站信息
	Siteinfo find();

	//增加网站访问次数
	void incViewCnt(Long viewCntWrite);

	//更新最后一次登录时间为现在的时间
	void update();

}
