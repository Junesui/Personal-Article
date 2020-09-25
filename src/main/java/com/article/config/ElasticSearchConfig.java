package com.article.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch配置类
 * @author June
 * @date 2020/09/19
 * @version V1.0
 */
@Configuration
public class ElasticSearchConfig {

	//ElasticSearch服务器的IP地址
	@Value("${es.ip}")
	private String esIp;
	//ElasticSearch服务器的端口
	@Value("${es.port}")
	private Integer esPort;
	//ElasticSearch服务器的协议
	@Value("${es.scheme}")
	private String esScheme;
	
	/**
	 * es高级客户端
	 * @return
	 */
	@Bean
	public RestHighLevelClient restHighLevelClient() {
		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(esIp, esPort, esScheme)));
		return client;
	}
	
	
}
