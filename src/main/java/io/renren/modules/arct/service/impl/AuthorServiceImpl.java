package io.renren.modules.arct.service.impl;

import io.renren.common.xss.XssHttpServletRequestWrapper;
import io.renren.modules.arct.entity.ArticleEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.AuthorDao;
import io.renren.modules.arct.entity.AuthorEntity;
import io.renren.modules.arct.service.AuthorService;

import javax.servlet.http.HttpServletRequest;


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

    @Override
    public List<AuthorEntity> selectLikeList(HttpServletRequest request) {
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);

        String id_list = orgRequest.getParameter("authorIds");
        String[] ids = id_list.split(",");
        List<AuthorEntity> like_list = new ArrayList<>();

        for (String id : ids) {
            Long aid = new Long(id);
            QueryWrapper<AuthorEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", aid);

            AuthorEntity authorEntity = baseMapper.selectOne(queryWrapper);
            like_list.add(authorEntity);
        }

        return like_list;

    }

}