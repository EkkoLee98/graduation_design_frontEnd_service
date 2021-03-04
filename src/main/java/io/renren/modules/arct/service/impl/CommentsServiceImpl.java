package io.renren.modules.arct.service.impl;

import io.renren.modules.arct.entity.ReplyEntity;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String authorId = (String)params.get("authorId");
        SysUserRoleEntity sysUserRoleEntity = sysUserRoleService.getById(Long.valueOf(authorId));

        Long roleId = sysUserRoleEntity.getRoleId();
        QueryWrapper<CommentsEntity> queryWrapper = new QueryWrapper<>();
        if (roleId != 1) {
            queryWrapper.eq("author_id", new Long(authorId));
        }
        IPage<CommentsEntity> page = this.page(
                new Query<CommentsEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

}