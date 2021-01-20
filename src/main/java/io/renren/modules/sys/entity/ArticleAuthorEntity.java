package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-01-20 15:54:46
 */
@Data
@TableName("article_author")
public class ArticleAuthorEntity implements Serializable {
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
	private String collectionCount;
	/**
	 * 
	 */
	private String commentsCount;
	/**
	 * 
	 */
	private String content;

}
