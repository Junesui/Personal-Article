package com.article.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * 编写一个适配器任务工厂，将实例化的任务手动添加到SpringIOC容器中完成对象的注入。
 * @author June
 * @date 2020/09/12
 * @version V1.0
 */
@Component("myAdaptableJobFactory")
public class MyAdaptableFactory extends AdaptableJobFactory {
	
	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		Object object = super.createJobInstance(bundle);
		//将实例化的任务手动添加到SpringIOC容器中
		this.autowireCapableBeanFactory.autowireBean(object);
		return object;
	}
}
