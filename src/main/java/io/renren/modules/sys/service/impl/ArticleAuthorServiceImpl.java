package io.renren.modules.sys.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.ArticleAuthorDao;
import io.renren.modules.sys.entity.ArticleAuthorEntity;
import io.renren.modules.sys.service.ArticleAuthorService;


@Service("articleAuthorService")
public class ArticleAuthorServiceImpl extends ServiceImpl<ArticleAuthorDao, ArticleAuthorEntity> implements ArticleAuthorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticleAuthorEntity> page = this.page(
                new Query<ArticleAuthorEntity>().getPage(params),
                new QueryWrapper<ArticleAuthorEntity>()
        );

        return new PageUtils(page);
    }

}