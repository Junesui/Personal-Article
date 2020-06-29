package com.article.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.article.dto.TagArticleNumDTO;
import com.article.entity.Tag;

/**
 * 标签mapper接口
 * @author June
 * @date 2020/05/12
 * @version V1.0
 */
@Mapper
public interface TagMapper {

	List<Tag> list();

	void saveArticleAndTag(Long articleId, Long tagId);

	void deleteByArticleId(Long articleId);

	Tag findByName(String name);

	void save(Tag tag);

	List<Tag> listByArticleId(Long articleId);

	Tag findById(Long id);

	void update(Tag tag);

	void deleteByTagId(Long id);

	void deleteById(Long id);

	Long count();

	List<TagArticleNumDTO> listTagAndArticle();

	Integer countAll();

}
