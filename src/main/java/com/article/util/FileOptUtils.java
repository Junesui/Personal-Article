package com.article.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传和展示工具类
 * @author June
 * @date 2020/07/12
 * @version V1.0
 */
public class FileOptUtils {

	/**
	 * 
	 * @param file 
	 * @param path 图片在Linux上存储的子路径（例如文章使用图片：{userId}/article，一句话使用的图片：{userId}/oneword。）
	 * @return 图片在Linux上存储的子路径加名称
	 * @throws Exception
	 */
	/**
	 * 文件上传
	 * @param file 上传的文件
	 * @param basePath 项目图片存放的根路径
	 * @param path 子路径
	 * @return 图片子路径或false
	 */
	public static String upload(MultipartFile file, String basePath, String path) {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		//拼接子路径
		String childPath = path + "/" + uuid;
		//图片存储路径
		File savedFile = new File(basePath, childPath);

		try {
			//以流的形式copy文件到目标路径
			FileUtils.copyInputStreamToFile(file.getInputStream(), savedFile);
			return childPath;
		} catch (IOException e) {
			return "false";
		}

	}

	/**
	 * 读取图片
	 * @param path 图片在服务器的存放路径
	 * @param response
	 */
	public static String read(String path, HttpServletResponse response) {
		File file = new File(path);
		try {
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.reset();

			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

			return "true";
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}

	}

}
