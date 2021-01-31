package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.common.xss.XssHttpServletRequestWrapper;
import io.renren.modules.sys.dao.ArticleAuthorDao;
import io.renren.modules.sys.entity.ArticleAuthorEntity;
import io.renren.modules.sys.service.ArticleAuthorService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Service("articleAuthorService")
public class ArticleAuthorServiceImpl extends ServiceImpl<ArticleAuthorDao, ArticleAuthorEntity> implements ArticleAuthorService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ArticleAuthorEntity> page = this.page(
                new Query<ArticleAuthorEntity>().getPage(params),
                new QueryWrapper<ArticleAuthorEntity>()
        );

        List<ArticleAuthorEntity> records = page.getRecords();
        for (ArticleAuthorEntity record : records) {


            String content = record.getContent();
            content = content.replaceAll("\"", "\\\"");
            record.setContent(content);
        }

        return new PageUtils(page);
    }

    @Override
    public void saveContent(HttpServletRequest request) {
        HttpServletRequest orgRequest = XssHttpServletRequestWrapper.getOrgRequest(request);
        // 富文本数据
        String content = orgRequest.getParameter("content");
        String classify = orgRequest.getParameter("classify");
        String id = orgRequest.getParameter("id");

        ArticleAuthorEntity entity = new ArticleAuthorEntity();
        entity.setContent(content);
        entity.setClassify(classify);

        if (id != null) {
            entity.setId(Long.valueOf(id));
        }

        saveOrUpdate(entity);
    }

}