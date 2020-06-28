package com.article.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import lombok.Data;

/**
 * 工具
 * @author June
 * @date 2020/06/24
 * @version V1.0
 */
@Data
public class Tool implements Serializable{
	
	private static final long serialVersionUID = 3458578602089193930L;

	private Integer id;
	
	@NotBlank(message = "请输入工具名称")
	@Length(max = 15,message = "工具名称不能超过15个字符")
	private String name;
	
	@URL(regexp = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?",
			 message = "请输入正确的工具地址格式")
	private String url;
	
	private Date createTime;
	private Date updateTime;
	private Boolean isDeleted;
}
