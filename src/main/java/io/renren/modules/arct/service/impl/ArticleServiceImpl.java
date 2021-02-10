package io.renren.modules.arct.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;

import io.renren.common.xss.XssHttpServletRequestWrapper;
import io.renren.modules.arct.entity.AuthorEntity;
import io.renren.modules.arct.entity.ClassifyEntity;
import io.renren.modules.arct.service.AuthorService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.renren.common.utils.Query;

import io.renren.modules.arct.dao.ArticleDao;
import io.renren.modules.arct.entity.ArticleEntity;
import io.renren.modules.arct.service.ArticleService;

import javax.servlet.http.HttpServletRequest;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ArticleService articleService;
    @Autowired
    private AuthorService authorService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String classify = (String)params.get("classify");

//        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
//        if (Query.STATUS_ACTIVED.equals(params.get(Query.STATUS))) {
//            queryWrapper.eq("status", 1);
//        }
//
//        IPage<ArticleEntity> page = this.page(
//                new Query<ArticleEntity>().getPage(params),
////                new QueryWrapper<ArticleEntity>()
//                queryWrapper
//        );

        IPage<ArticleEntity> page = this.page(
                new Query<ArticleEntity>().getPage(params),
                new QueryWrapper<ArticleEntity>()
                        .like(StringUtils.isNotBlank(classify),"classify", classify)
        );


        return new PageUtils(page);
    }

    @Override
    public void saveOption(HttpServletRequest request) {
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);

        String id = orgRequest.getParameter("id");
        String aid = orgRequest.getParameter("aid");
        String type = orgRequest.getParameter("type");
        String status = orgRequest.getParameter("status");
        ArticleEntity articleEntity = articleService.getById(id);
        AuthorEntity authorEntity = authorService.getById(aid);
        AuthorEntity authorEntityTo = authorService.getById(articleEntity.getAuthorId());
        String aidTo = String.valueOf(articleEntity.getAuthorId());

        if (type.equals("fan")) {
            String count = authorEntityTo.getFansCount();
            if (Integer.parseInt(status) == 1) {
                int tmpCount = Integer.parseInt(count) + 1;
                String saveCount = String.valueOf(tmpCount);
                authorEntityTo.setFansCount(saveCount);
                String strs = authorEntity.getAuthorLikesIds();
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs.split(",")));
                if (!list.contains(aidTo)) {
                    list.add(aidTo);
                }
                String list_str = StringUtils.join(list,",");
                authorEntity.setAuthorLikesIds(list_str);
                authorService.saveOrUpdate(authorEntity);
                authorService.saveOrUpdate(authorEntityTo);
            }
            if (Integer.parseInt(status) == 0) {
                int tmpCount = Integer.parseInt(count) - 1;
                String saveCount = String.valueOf(tmpCount);
                authorEntityTo.setFansCount(saveCount);
                String strs = authorEntity.getAuthorLikesIds();
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs.split(",")));
                list.remove(aidTo);
                String list_str = StringUtils.join(list,",");
                authorEntity.setAuthorLikesIds(list_str);
                authorService.saveOrUpdate(authorEntity);
                authorService.saveOrUpdate(authorEntityTo);
            }
        }

//        ArticleEntity entity = new ArticleEntity();
        if (type.equals("thumbsup")) {
            String count = articleEntity.getThumbsUpCount();
            if (Integer.parseInt(status) == 1) {
                int tmpCount = Integer.parseInt(count) + 1;
                String saveCount = String.valueOf(tmpCount);
                articleEntity.setThumbsUpCount(saveCount);
                String strs = authorEntity.getThumpsUpArticleIds();
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs.split(",")));
                if (!list.contains(id)) {
                    list.add(id);
                }
                String list_str = StringUtils.join(list,",");
                authorEntity.setThumpsUpArticleIds(list_str);
                saveOrUpdate(articleEntity);
                authorService.saveOrUpdate(authorEntity);
            }
            if (Integer.parseInt(status) == 0) {
                int tmpCount = Integer.parseInt(count) - 1;
                String saveCount = String.valueOf(tmpCount);
                articleEntity.setThumbsUpCount(saveCount);
                String strs = authorEntity.getThumpsUpArticleIds();
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs.split(",")));
                list.remove(id);
                String list_str = StringUtils.join(list,",");
                authorEntity.setThumpsUpArticleIds(list_str);
                saveOrUpdate(articleEntity);
                authorService.saveOrUpdate(authorEntity);
            }
        }

        if (type.equals("like")) {
            String count = articleEntity.getCollectionCount();
            if (Integer.parseInt(status) == 1) {
                int tmpCount = Integer.parseInt(count) + 1;
                String saveCount = String.valueOf(tmpCount);
                articleEntity.setCollectionCount(saveCount);
                String strs = authorEntity.getArticleLikesIds();
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs.split(",")));
                if (!list.contains(id)) {
                    list.add(id);
                }
                String list_str = StringUtils.join(list,",");
                authorEntity.setArticleLikesIds(list_str);
                saveOrUpdate(articleEntity);
                authorService.saveOrUpdate(authorEntity);
            }
            if (Integer.parseInt(status) == 0) {
                int tmpCount = Integer.parseInt(count) - 1;
                String saveCount = String.valueOf(tmpCount);
                articleEntity.setCollectionCount(saveCount);
                String strs = authorEntity.getArticleLikesIds();
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(strs.split(",")));
                list.remove(id);
                String list_str = StringUtils.join(list,",");
                authorEntity.setArticleLikesIds(list_str);
                saveOrUpdate(articleEntity);
                authorService.saveOrUpdate(authorEntity);
            }
        }


//        if (id != null) {
//            entity.setId(Long.valueOf(id));
//        }
//
//        saveOrUpdate(entity);
    }

}