package com.article.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串和列表互相转换。字符串格式:　1,2,3
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public class StringAndListConvertUtils {

	/**
	 * 字符串转列表
	 * @param ids
	 * @return List或 Null
	 */
	public static List<Long> toList(String ids){
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
	
	/**
	 * 列表转字符串
	 * @param list
	 * @return String或Null
	 */
	public static String toString(List<Long> list) {
		if (!list.isEmpty()) {
			StringBuffer ids = new StringBuffer();
			boolean flag = false;
			for (Long l : list) {
				if (flag) {
					ids.append(",");
				}else {
					flag = true;
				}
				ids.append(l);
			}
			return ids.toString();
		}else {
			return null;
		}
	}
	
}
