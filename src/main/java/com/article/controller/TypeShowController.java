package com.article.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.article.dto.ArticleTypeTagDTO;
import com.article.dto.TypeArticleNumDTO;
import com.article.service.ArticleService;
import com.article.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 分类展示控制器
 * @author June
 * @date 2020/05/17
 * @version V1.0
 */
@Controller
public class TypeShowController {

	@Autowired
	private TypeService typeService;
	@Autowired
	private ArticleService articleService;
	
	//分类页展示的文章数量
	@Value("${types.pageArticleSize}")
	private Integer pageArticleSize;
	
	/**
	 * 跳转到分类页面
	 * @param page
	 * @param size
	 * @param id
	 * @param model
	 * @return 分类页面
	 */
	@GetMapping("/types/{id}")
	public String toTypes(@RequestParam(name = "page", defaultValue = "1") Integer page,
			            @RequestParam(name = "size", defaultValue = "8") Integer size, 
			            @PathVariable Long id, Model model) {
		size = pageArticleSize;
		//统计有文章的分类总数
		Long typeCnt = typeService.count();
		
		List<TypeArticleNumDTO> types = typeService.listTypeShow();
		if (id == -1) {
			id = types.get(0).getId();
		}

		//分页
		PageHelper.startPage(page, size);
		List<ArticleTypeTagDTO> articleTypeTagDTO = articleService.listTypeArticleByTypeId(id);
		
		PageInfo<ArticleTypeTagDTO> pageInfo = new PageInfo<>(articleTypeTagDTO);
		
		model.addAttribute("typeCnt", typeCnt);
		model.addAttribute("types", types);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("activeTypeId", id);
		return "types";
	}
}
