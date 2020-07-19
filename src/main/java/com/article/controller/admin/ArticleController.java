package com.article.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.article.constant.PictureTypeConst;
import com.article.dto.ArticleQueryDTO;
import com.article.dto.ArticleTypeTagDTO;
import com.article.entity.Tag;
import com.article.entity.User;
import com.article.service.ArticleService;
import com.article.service.TagService;
import com.article.service.TypeService;
import com.article.util.FileOptUtils;
import com.article.util.StringAndListConvertUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 后台文章管理控制器
 * @author June
 * @date 2020/05/11
 * @version V1.0
 */
@Controller
@RequestMapping("/1120")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private TagService tagService;


	//文章后台管理每页展示的文章数量
	@Value("${admin.pageArticleSize}")
	private Integer pageArticleSize;
	//项目图片存放的根路径
	@Value("${picture.basePath}")
	private String basePath;
	//允许上传图片的大小
	@Value("${picture.maxSize}")
	private Long maxSize;
	
	/**
	 * 跳转到后台首页
	 * @return 后台首页
	 */
	@GetMapping("/index")
	public String toIndex() {
		return "admin/index";
	}

	/**
	 * 跳转到文章管理页面
	 * @param pageNum 页码
	 * @param pageSize 每页数据的数量
	 * @param model
	 * @return 文章管理页面
	 */
	@GetMapping("/articles")
	public String toArticles(@RequestParam(name = "page", defaultValue = "1") Integer page,
			   		      @RequestParam(name = "size", defaultValue = "10") Integer size, 
			   		      Model model) {
		size = pageArticleSize;
		//分页
		PageHelper.startPage(page, size);
		List<ArticleTypeTagDTO> articleTypeDTOs = articleService.listArticleAndType();

		PageInfo<ArticleTypeTagDTO> pageInfo = new PageInfo<>(articleTypeDTOs);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("types", typeService.list());
		return "admin/articles";
	}

	/**
	 * 跳转到文章发布页面
	 * @return
	 */
	@GetMapping("/articles/add")
	public String articlesAdd(Model model) {
		model.addAttribute("articleTypeTagDTO", new ArticleTypeTagDTO());
		model.addAttribute("types", typeService.list());
		model.addAttribute("tags", tagService.list());
		return "admin/article-release";
	}

	/**
	 * 发布文章
	 * @param article
	 * @param session
	 * @param attributes
	 * @return 文章管理页面
	 */
	@PostMapping("/articles")
	public String post(@RequestParam("picFile") MultipartFile file, @Validated ArticleTypeTagDTO articleTypeTagDTO,
			BindingResult result, HttpSession session, RedirectAttributes attributes) {

		//字段验证
		if (result.hasErrors()) {
			return "admin/article-release";
		}

		User user = (User) session.getAttribute("user");
		articleTypeTagDTO.setUserId(user.getId());

		//获取文件大小并转换单位为M
		long size = file.getSize() / 1024 / 1024;
		if (size > maxSize) {
			attributes.addFlashAttribute("upFailMsg", "图片大小超过限制");
			return "redirect:/1120/articles/add";
		} else {
			//上传图片
			String childPath = FileOptUtils.upload(file, basePath, "userId" + user.getId() + PictureTypeConst.articleDir);
			if ("false".equals(childPath)) {
				attributes.addFlashAttribute("upFailMsg", "图片上传失败");
				return "redirect:/1120/articles/add";
			}
			//为了读取，把斜杠/换成横杠-
			String firstPicture = (basePath + "/" + childPath).replaceAll("/", "-");
			//设置首图地址
			articleTypeTagDTO.setFirstPicture(firstPicture);
			//保存或更新文章
			articleService.saveOrUpdate(articleTypeTagDTO);

			attributes.addFlashAttribute("message", "操作成功");
			return "redirect:/1120/articles";
		}

	}
	
	/**
	 * 后台查找文章
	 * @param pageNum
	 * @param pageSize
	 * @param articleQueryDTO
	 * @param model
	 * @return 文章管理页面的文章列表片段
	 */
	@PostMapping("/articles/search")
	public String search(@RequestParam(name = "page", defaultValue = "1") Integer page,
			             @RequestParam(name = "size", defaultValue = "10") Integer size, 
			             ArticleQueryDTO articleQueryDTO, Model model) {
		size = pageArticleSize;
		//分页
		PageHelper.startPage(page, size);
		List<ArticleTypeTagDTO> dto = articleService.listBySearch(articleQueryDTO);
		PageInfo<ArticleTypeTagDTO> pageInfo = new PageInfo<>(dto);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/articles :: articleList";
	}

	/**
	 * 跳转到文章编辑页面
	 * @param id
	 * @param model
	 * @return 文章编辑页面
	 */
	@GetMapping("/articles/edit/{id}")
	public String articleEdit(@PathVariable("id") Long id, Model model) {
		//通过文章id查找文章和分类
		ArticleTypeTagDTO articleTypeTagDTO = articleService.findArticleAndTypeById(id);

		//通过文章id查找对应的标签id
		List<Tag> tags = tagService.listByArticleId(id);
		List<Long> tagList = new ArrayList<Long>();
		for (Tag tag : tags) {
			tagList.add(tag.getId());
		}
		//列表tagids转换为字符串
		String tagIds = StringAndListConvertUtils.toString(tagList);

		model.addAttribute("articleTypeTagDTO", articleTypeTagDTO);
		model.addAttribute("tagIds", tagIds);
		model.addAttribute("types", typeService.list());
		model.addAttribute("tags", tagService.list());
		return "admin/article-release";
	}

	/**
	 * 根据id删除文章
	 * @param id
	 * @param model
	 * @return 文章管理页面
	 */
	@GetMapping("/articles/delete/{id}")
	public String articleDelete(@PathVariable Long id, RedirectAttributes attributes) {
		articleService.deleteById(id);
		attributes.addFlashAttribute("message", "删除成功");
		return "redirect:/1120/articles";
	}

}
