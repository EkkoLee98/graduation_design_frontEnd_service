package io.renren.modules.arct.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
	private String browseCount;
	/**
	 * 
	 */
	private String classify;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private String thumbsUpCount;
	/**
	 * 
	 */
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
	private String comments;
	/**
	 * 
	 */
	private String collectionCount;
	/**
	 * 
	 */
	private String commentsCount;
	/**
	 * 
	 */
	private String cover;

}
