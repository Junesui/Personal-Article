package com.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  //开启定时器任务
public class ArticleApplication{
	public static void main(String[] args) {
		SpringApplication.run(ArticleApplication.class, args);
	}
}
