package io.renren.modules.arct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.arct.entity.IntegralOrderEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-16 01:51:00
 */
public interface IntegralOrderService extends IService<IntegralOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

