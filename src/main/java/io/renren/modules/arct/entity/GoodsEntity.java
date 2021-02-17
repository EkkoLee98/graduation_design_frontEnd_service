package io.renren.modules.arct.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("goods")
public class GoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer goodId;
	/**
	 * 
	 */
	private String goodName;
	/**
	 * 
	 */
	private BigDecimal goodPrice;
	/**
	 * 
	 */
	private String goodImg;
	/**
	 *
	 */
	private Long goodIntegral;
	/**
	 *
	 */
	private String goodClassify;

}
