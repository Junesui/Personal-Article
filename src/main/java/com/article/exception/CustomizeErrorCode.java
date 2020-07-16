package com.article.exception;

/**
 * 自定义错误码
 * @author June
 * @date 2020/05/27
 * @version V1.0
 */
public enum CustomizeErrorCode {

	SYS_ERROR(1120,"服务器异常，请稍后再来试试！"),
	ARTICLE_NOT_FOUND(1121,"要找的文章走丢了！");
	
	private Integer code;
	private String messsage;
	
	
	private CustomizeErrorCode(String messsage) {
		this.messsage = messsage;
	}

	CustomizeErrorCode(Integer code, String message) {
		this.code = code;
		this.messsage = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMesssage() {
		return messsage;
	}
	
}
