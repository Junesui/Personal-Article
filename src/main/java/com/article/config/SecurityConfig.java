package com.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author June
 * @date 2020/08/24
 * @version V1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//加密密码
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//安全拦截机制
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/*","/1120/toLogin").permitAll() //请求都可以访问
				//.antMatchers("/1120/friendslink").hasAuthority("p")
				.antMatchers("/1120/**").authenticated()
				.and()
				.formLogin()
				.loginPage("/1120/toLogin") //自定义登录的url
				.loginProcessingUrl("/1120/login") //自定义登录form的action名字。
				.defaultSuccessUrl("/1120/index", true) //登陆成功后跳转的url
				.and()
				.exceptionHandling().accessDeniedPage("/403")
				.and()
				.logout()
				.logoutUrl("/1120/logout") //自定义注销的url
				.logoutSuccessUrl("/1120/myLogout") //注销成功后跳转的url
				.and()
				.rememberMe()
				.and()
				.csrf().disable();
	}

}
