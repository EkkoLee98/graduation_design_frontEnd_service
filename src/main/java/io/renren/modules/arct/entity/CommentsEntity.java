package io.renren.modules.arct.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-10 15:24:43
 */
@Data
@TableName("comments")
public class CommentsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String commentContent;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private String isReply;
	/**
	 * 
	 */
	private Long authorId;
	/**
	 *
	 */
	private Long articleId;
	/**
	 *
	 */
	private Long rate;
	/**
	 *
	 */
	@TableField(exist = false)
	private List<Object> replys;

}
