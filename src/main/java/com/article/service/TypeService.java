package com.article.service;

import java.util.List;

import com.article.dto.TypeArticleNumDTO;
import com.article.entity.Type;

/**
 * 分类service接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
public interface TypeService {

	//列出所有类型
	List<Type> list();

	//通过名字查找分类
	Type findByName(String name);

	//保存分类
	void save(Type type);

	//通过id查找分类
	Type findById(Long id);

	//更新分类
	void update(Type type);

	//通过id删除分类
	void deleteById(Long id);

	//统计有文章的分类总数
	Long count();

	//列出分类及对应的文章数量
	List<TypeArticleNumDTO> listTypeAndArticle();

}
