package io.renren.modules.arct.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.ArticleDao;
import io.renren.modules.arct.entity.ArticleEntity;
import io.renren.modules.arct.service.ArticleService;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        if (Query.STATUS_ACTIVED.equals(params.get(Query.STATUS))) {
            queryWrapper.eq("status", 1);
        }

        IPage<ArticleEntity> page = this.page(
                new Query<ArticleEntity>().getPage(params),
                queryWrapper
        );


        return new PageUtils(page);
    }

}