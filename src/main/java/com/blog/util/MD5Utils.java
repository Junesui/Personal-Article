package com.blog.util;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * MD5加密
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public class MD5Utils {

	public static String code(String str) {
        try {           
            MessageDigest md = MessageDigest.getInstance("MD5");        
            md.update(str.getBytes());  
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
           e.printStackTrace();
           return null;
        }
    }
}
