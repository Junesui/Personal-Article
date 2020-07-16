package com.article.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.article.util.FileOptUtils;

/**
 * 全局公用控制器
 * @author June
 * @date 2020/07/16
 * @version V1.0
 */
@Controller
public class CommonController {

	/**
	 * 图片展示
	 * @param firstPictrue 图片地址
	 * @param response
	 * @return 
	 */
	@ResponseBody
	@GetMapping("/show/{firstPictrue}")
	public Object show(@PathVariable("firstPictrue") String firstPictrue, HttpServletResponse response) {
		String path = firstPictrue.replaceAll("-", "/");
		String result = FileOptUtils.read(path, response);

		Map<String, String> map = new HashMap<String, String>();
		map.put("result", result);
		return map;
	}

}
