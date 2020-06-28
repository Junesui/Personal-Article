package com.article.exception;

/**
 * 自定义异常类
 * @author June
 * @date 2020/05/27
 * @version V1.0
 */
public class CustomizeException extends RuntimeException {

	private Integer code;
	private String message;
	
	public CustomizeException(CustomizeErrorCode errorCode) {
		this.code = errorCode.getCode();
		this.message = errorCode.getMesssage();
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	

}
