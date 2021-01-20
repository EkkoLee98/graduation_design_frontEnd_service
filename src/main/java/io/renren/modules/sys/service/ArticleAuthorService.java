package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.ArticleAuthorEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-01-20 15:54:46
 */
public interface ArticleAuthorService extends IService<ArticleAuthorEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

