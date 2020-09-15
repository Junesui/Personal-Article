package com.article.quartz;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.article.entity.Article;
import com.article.mapper.ArticleMapper;
import com.article.util.RedisUtil;

/**
 * 文章的定时器任务
 * @author June
 * @date 2020/09/12
 * @version V1.0
 */
@Component
public class ArticleJob extends QuartzJobBean {

	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private ArticleMapper articleMapper;
	
	//redis中存文章点赞数的key名
	@Value("${article.likeCount.key}")
	private String articleLikeCountKey;
	//redis中存文章点赞数的field名 ["articleId-" + 文章id]
	@Value("${article.likeCount.field}")
	private String articleLikeCountField;
	//redis中存文章点赞数的field名的前缀长度
	@Value("${articleLikeCnt.field.prefixLength}")
	private Integer prefixLength;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		
		Map<Object, Object> articleLikeCounts = redisUtil.hGetAll(articleLikeCountKey);
		for(Map.Entry<Object, Object> entry : articleLikeCounts.entrySet()) {
			//截取文章id
			String field = entry.getKey().toString();
			Long articleId = Long.parseLong(StringUtils.truncate(field, prefixLength, Integer.MAX_VALUE));
			//redis中文章的点赞数
			Integer rArticleLikeCnt = (Integer)entry.getValue();
			//从mysql数据库获取文章点赞数
			Article article = articleMapper.findArticleLikeCntById(articleId);
			Integer mArticleLikeCnt = article.getLikeCount();
			
			if (mArticleLikeCnt != rArticleLikeCnt) {
				//如果有新的点赞数，则把redis中点赞数更新的mysql数据库
				article.setId(articleId);
				articleMapper.updateLikeCnt(article);
			}
			
		}
		
	}

}
