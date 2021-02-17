package io.renren.modules.arct.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 
 * 
 * @author zes
 * @email 779732613@qq.com
 * @date 2021-02-01 01:56:04
 */
@Data
@TableName("article")
public class ArticleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 作者关联ID
	 */
	private Long authorId;
	/**
	 * 
	 */
	private Long browseCount;
	/**
	 * 
	 */
	@NotBlank(message="类型不能为空")
	private String classify;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Long thumbsUpCount;
	/**
	 * 
	 */
	@NotBlank(message="标题不能为空")
	private String title;
	/**
	 * 
	 */
	private String mode;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Long collectionCount;
	/**
	 * 
	 */
	private String cover;
	/**
	 *
	 */
	@TableField(exist = false)
	private List<Object> comments;

}
