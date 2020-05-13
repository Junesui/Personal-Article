package com.blog.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串转列表　"1,2,3"这种格式的字符串
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public class StringToListUtils {

	public static List<Long> convert(String ids){
		List<Long> idList = new ArrayList<Long>();
		
		if (StringUtils.isNotEmpty(ids)) {
			String[] isArr = ids.split(",");
			for (int i = 0; i < isArr.length; i++) {
				idList.add(new Long(isArr[i]));
			}
			return idList;
		}
		return null;
	}
	
}
