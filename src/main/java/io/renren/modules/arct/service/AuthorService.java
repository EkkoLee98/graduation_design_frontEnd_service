package io.renren.modules.arct.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.arct.entity.ArticleEntity;
import io.renren.modules.arct.entity.AuthorEntity;

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
public interface AuthorService extends IService<AuthorEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<AuthorEntity> selectLikeList(HttpServletRequest request);
}

