package com.article.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.article.quartz.ArticleJob;
import com.article.quartz.MyAdaptableFactory;

/**
 * Quartz配置类
 * @author June
 * @date 2020/09/12
 * @version V1.0
 */
@Configuration
public class QuartzConfig {
	
	//文章点赞定时器任务Cron
	@Value("${quartz.articleLikeCountCron}")
	private String articleLikeCountCron;
	
	//创建Job对象
	@Bean
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean factory = new JobDetailFactoryBean();
		factory.setJobClass(ArticleJob.class);
		return factory;
	}

	//创建Trigger对象
	@Bean
	public CronTriggerFactoryBean cronTriggerFactoryBean(JobDetailFactoryBean jobDetialFactoryBean) {
		CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
		factory.setJobDetail(jobDetialFactoryBean.getObject());
		factory.setCronExpression(articleLikeCountCron);
		return factory;
	}

	//创建Scheduler对象
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean,
			MyAdaptableFactory myAdaptableFactory) {
		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setTriggers(cronTriggerFactoryBean.getObject());
		factory.setJobFactory(myAdaptableFactory);
		return factory;
	}
	
}
