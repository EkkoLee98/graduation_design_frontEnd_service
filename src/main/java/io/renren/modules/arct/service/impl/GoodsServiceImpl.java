package io.renren.modules.arct.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.GoodsDao;
import io.renren.modules.arct.entity.GoodsEntity;
import io.renren.modules.arct.service.GoodsService;


@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        QueryWrapper<GoodsEntity> queryWrapper = new QueryWrapper<>();
        if (Query.STATUS_ACTIVED.equals(params.get(Query.STATUS))) {
            queryWrapper.eq("status", 1);
        }

        IPage<GoodsEntity> page = this.page(
                new Query<GoodsEntity>().getPage(params),
                queryWrapper
        );


        return new PageUtils(page);
    }

}