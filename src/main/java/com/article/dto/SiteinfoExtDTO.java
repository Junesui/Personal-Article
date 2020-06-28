package com.article.dto;

import java.util.Date;

import com.article.entity.Siteinfo;

import lombok.Data;

/**
 * 网站信息
 * @author June
 * @date 2020/06/27
 * @version V1.0
 */
@Data
public class SiteinfoExtDTO extends Siteinfo{

	public Long runTime;
	public Integer articleCount;
	public Integer onewordCount;
	public Integer friendslinkCount;
	public Integer commentCount;
	public Integer messageCount;
	public Integer toolCount;
	public Integer typeCount;
	public Integer tagCount;
}
