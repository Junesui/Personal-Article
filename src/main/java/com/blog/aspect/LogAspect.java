package com.blog.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 日志切面
 * @author June
 * @date 2020/05/24
 * @version V1.0
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

	@Pointcut("execution(* com.blog.controller.*.*(..)) || execution(* com.blog.service.*.*(..))")
	public void log() {}
	
	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String url = request.getRequestURL().toString();
		String ip = request.getRemoteAddr();
		String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
		log.info("Request : {}", requestLog);
	}
	
	@After("log()")
	public void doAfter() {
		
	}
	
	@AfterReturning(returning = "result",pointcut = "log()")
	public void doAfterRuturn(Object result) {
		log.info("Result : {}", result);
	}
	
	
	@Data
	@AllArgsConstructor
	private class RequestLog{
		private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
	}
	

}
