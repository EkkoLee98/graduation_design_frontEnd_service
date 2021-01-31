package io.renren.modules.arct.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.GoodWeightDao;
import io.renren.modules.arct.entity.GoodWeightEntity;
import io.renren.modules.arct.service.GoodWeightService;


@Service("goodWeightService")
public class GoodWeightServiceImpl extends ServiceImpl<GoodWeightDao, GoodWeightEntity> implements GoodWeightService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<GoodWeightEntity> queryWrapper = new QueryWrapper<>();
        if (Query.STATUS_ACTIVED.equals(params.get(Query.STATUS))) {
            queryWrapper.eq("status", 1);
        }

        IPage<GoodWeightEntity> page = this.page(
                new Query<GoodWeightEntity>().getPage(params),
                queryWrapper
        );


        return new PageUtils(page);
    }

}