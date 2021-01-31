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
@TableName("good_weight")
public class GoodWeightEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 主板权重
	 */
	private String mainBoard;
	/**
	 * cpu权重
	 */
	private String cpu;
	/**
	 * 显卡权重
	 */
	private String graphicsCard;
	/**
	 * 内存权重
	 */
	private String memory;
	/**
	 * 电源权重
	 */
	private String 


powerSupply;
	/**
	 * 是否使用此套权重
	 */
	private Integer usingStatus;

}
