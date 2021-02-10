package io.renren.modules.arct.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.ReplyDao;
import io.renren.modules.arct.entity.ReplyEntity;
import io.renren.modules.arct.service.ReplyService;


@Service("replyService")
public class ReplyServiceImpl extends ServiceImpl<ReplyDao, ReplyEntity> implements ReplyService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ReplyEntity> page = this.page(
                new Query<ReplyEntity>().getPage(params),
                new QueryWrapper<ReplyEntity>()
        );

        return new PageUtils(page);
    }

}