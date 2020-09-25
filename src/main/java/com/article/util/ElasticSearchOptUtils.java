package com.article.util;

import java.io.IOException;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.article.dto.ArticleTypeTagDTO;
import com.article.mapper.ArticleMapper;
import com.article.mapper.TypeMapper;
import com.article.mapper.UserMapper;

/**
 * 往ES中执行插入或更新操作
 * @author June
 * @date 2020/09/21
 * @version V1.0
 */
@Component
public class ElasticSearchOptUtils {

	@Autowired
	private RestHighLevelClient restHighLevelClient;
	@Autowired
	private TypeMapper typeMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ArticleMapper articleMapper;
	

	//ElasticSearch中文章的索引名
	@Value("${es.indexName}")
	private String esIndexName;

	/**
	 * 往ES中插入数据
	 * @param dto
	 */
	public void insert(ArticleTypeTagDTO dto) {
		GetIndexRequest getIndexRequest = new GetIndexRequest(esIndexName);
		try {
			//判断索引是否存在
			boolean exists = restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
			if (!exists) {
				//如果不存在就创建
				CreateIndexRequest createIndexRequest = new CreateIndexRequest(esIndexName);
				restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
			}
			//添加字段为null的数据
			dto.setTypeName(typeMapper.findById(dto.getTypeId()).getName());
			dto.setUserAvatar(userMapper.findById(dto.getUserId()).getAvatar());
			dto.setUserNickname(userMapper.findById(dto.getUserId()).getNickname());
			dto.setLikeCount(articleMapper.findById(dto.getId()).getLikeCount());

			IndexRequest indexRequest = new IndexRequest(esIndexName);
			//设置id
			indexRequest.id(Long.toString(dto.getId()));
			//设置数据
			indexRequest.source(JSON.toJSONString(dto), XContentType.JSON);
			//执行存入请求
			restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过ID更新ES中的数据
	 * @param dto
	 * @param id 文章ID
	 */
	public void update(ArticleTypeTagDTO dto, Long id) {

		UpdateRequest updateRequest = new UpdateRequest(esIndexName, Long.toString(id));

		//构建对象
		/*Article esArticle = new Article();
		esArticle.setFirstPicture(dto.getFirstPicture());
		esArticle.setTitle(dto.getTitle());
		esArticle.setDescription(dto.getDescription());*/

		updateRequest.doc(JSON.toJSONString(dto), XContentType.JSON);

		try {
			//执行更新请求
			restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
