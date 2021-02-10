package io.renren.modules.arct.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
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
@TableName("classify")
public class ClassifyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@NotBlank(message="类型id不能为空")
	private Integer classifyId;
	/**
	 * 
	 */
	private String classify;

}
