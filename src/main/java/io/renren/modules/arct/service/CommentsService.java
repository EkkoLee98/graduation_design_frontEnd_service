package io.renren.modules.arct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.arct.entity.CommentsEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-10 15:24:43
 */
public interface CommentsService extends IService<CommentsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

