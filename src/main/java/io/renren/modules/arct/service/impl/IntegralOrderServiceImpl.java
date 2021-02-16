package io.renren.modules.arct.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.IntegralOrderDao;
import io.renren.modules.arct.entity.IntegralOrderEntity;
import io.renren.modules.arct.service.IntegralOrderService;


@Service("integralOrderService")
public class IntegralOrderServiceImpl extends ServiceImpl<IntegralOrderDao, IntegralOrderEntity> implements IntegralOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<IntegralOrderEntity> page = this.page(
                new Query<IntegralOrderEntity>().getPage(params),
                new QueryWrapper<IntegralOrderEntity>()
        );

        return new PageUtils(page);
    }

}