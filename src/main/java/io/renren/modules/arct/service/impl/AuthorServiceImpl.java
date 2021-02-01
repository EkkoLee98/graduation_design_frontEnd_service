package io.renren.modules.arct.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.AuthorDao;
import io.renren.modules.arct.entity.AuthorEntity;
import io.renren.modules.arct.service.AuthorService;


@Service("authorService")
public class AuthorServiceImpl extends ServiceImpl<AuthorDao, AuthorEntity> implements AuthorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<AuthorEntity> queryWrapper = new QueryWrapper<>();
        if (Query.STATUS_ACTIVED.equals(params.get(Query.STATUS))) {
            queryWrapper.eq("status", 1);
        }

        IPage<AuthorEntity> page = this.page(
                new Query<AuthorEntity>().getPage(params),
//                new QueryWrapper<AuthorEntity>()
                queryWrapper
        );


        return new PageUtils(page);
    }

}