package com.article.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

import com.article.service.impl.CustomUserDetailsService;

/**
 * 
 * @author June
 * @date 2020/08/24
 * @version V1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	//用户登录成功后token存活秒数
	@Value("${token.ValiditySeconds}")
	private Integer tokenValiditySeconds;
	
	//加密密码
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    //允许多请求地址多加斜杠  比如 /msg/list   //msg/list
    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }
    
    //记住我功能的相关配置
    @Bean
	public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        // 将 DataSource 设置到 PersistentTokenRepository
        tokenRepository.setDataSource(dataSource);
        // 第一次启动的时候自动建表（可以不用这句话，自己手动建表，源码中有语句的）
        // persistentTokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
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
				.tokenRepository(persistentTokenRepository()) // 配置数据库源
				.tokenValiditySeconds(tokenValiditySeconds)
				.userDetailsService(userDetailsService)
				.and()
				.csrf().disable();
	}

}
