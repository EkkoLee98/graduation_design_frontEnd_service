package io.renren.modules.arct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.arct.entity.ClassifyEntity;

import java.util.Map;

/**
 * 
 *
 * @author zes
 * @email 779732613@qq.com
 * @date 2021-02-01 01:56:04
 */
public interface ClassifyService extends IService<ClassifyEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

