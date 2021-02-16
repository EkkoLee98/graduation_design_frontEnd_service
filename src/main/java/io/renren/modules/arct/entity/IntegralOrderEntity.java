package io.renren.modules.arct.entity;

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
 * @date 2021-02-16 01:51:00
 */
@Data
@TableName("integral_order")
public class IntegralOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String goodName;
	/**
	 *
	 */
	private Long authorId;
	/**
	 * 
	 */
	private String authorName;
	/**
	 * 
	 */
	private Long integral;
	/**
	 *
	 */
	private String address;
}
