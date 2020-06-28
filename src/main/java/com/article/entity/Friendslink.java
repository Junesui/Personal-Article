package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.Data;

/**
 * 友人链实体类
 * @author June
 * @date 2020/06/21
 * @version V1.0
 */
@Data
public class Friendslink implements Serializable{

	private static final long serialVersionUID = 9115692006126892489L;
	
	public Long id;
	
	@URL(regexp = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?",
		 message = "请输入正确的网站地址格式")
	public String websiteUrl;
	
	@NotBlank(message = "请输入网站名称")
	@Length(max = 16,message = "网站名称不能超过16个字符")
	public String websiteName;  
	
	@NotBlank(message = "请输入网站描述")
	@Length(max = 28,message = "网站描述不能超过28个字符")
	public String websiteDescription;
	
	@URL(regexp = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?",
			 message = "请输入正确的图片地址格式")
	public String pictureUrl;
	
	public Boolean isShow;            
	public Integer priority;
	public String groups;             
	public Date createTime;        
	public Date updateTime;        
	public Long viewCount;
	
}
