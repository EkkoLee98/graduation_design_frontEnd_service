package io.renren.modules.arct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.arct.entity.ArticleEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author zes
 * @email 779732613@qq.com
 * @date 2021-02-01 01:56:04
 */
public interface ArticleService extends IService<ArticleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveOption(HttpServletRequest request);

    List<ArticleEntity> selectLikeList(HttpServletRequest request);

    List<ArticleEntity> selectMyList(HttpServletRequest request);
}

