package io.renren.modules.arct.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.CommentsDao;
import io.renren.modules.arct.entity.CommentsEntity;
import io.renren.modules.arct.service.CommentsService;


@Service("commentsService")
public class CommentsServiceImpl extends ServiceImpl<CommentsDao, CommentsEntity> implements CommentsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String authorId = (String)params.get("authorId");
        IPage<CommentsEntity> page = this.page(
                new Query<CommentsEntity>().getPage(params),
                new QueryWrapper<CommentsEntity>()
                        .eq("author_id", new Long(authorId))
        );

        return new PageUtils(page);
    }

}