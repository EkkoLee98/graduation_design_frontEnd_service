package io.renren.modules.arct.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import io.renren.modules.arct.entity.AuthorEntity;
import io.renren.modules.arct.service.AuthorService;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.arct.entity.ArticleEntity;
import io.renren.modules.arct.service.ArticleService;
import io.renren.modules.arct.entity.CommentsEntity;
import io.renren.modules.arct.service.CommentsService;
import io.renren.modules.arct.service.ReplyService;
import io.renren.modules.arct.entity.ReplyEntity;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author zes
 * @email 779732613@qq.com
 * @date 2021-02-01 01:56:04
 */
@RestController
@Api(value = "")
@RequestMapping("arct/article")
public class ArticleController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private AuthorService authorService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("arct:article:list")
    @ApiOperation(value = "查询列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 喜欢的文章列表
     */
    @PostMapping("/list/like")
    @RequiresPermissions("arct:article:list")
    public R like_list(HttpServletRequest request) {

        return R.ok().put("articles", articleService.selectLikeList(request));
    }

    /**
     * 我的文章列表
     */
    @PostMapping("/list/my")
    @RequiresPermissions("arct:article:list")
    public R my_list(HttpServletRequest request) {

        return R.ok().put("articles", articleService.selectMyList(request));
    }


    /**
     * 根据id查询信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("arct:article:info")
    @ApiOperation(value = "根据id查询信息")
    public R info(@PathVariable("id") Long id){
		ArticleEntity article = articleService.getById(id);
		long browse_count = article.getBrowseCount() + 1;
//		long count = browse_count + 1;
		article.setBrowseCount(browse_count);

//		CommentsEntity commentsEntity = commentsService
        List<CommentsEntity> list = commentsService.list(new QueryWrapper<CommentsEntity>().eq("article_id", id));
        for (CommentsEntity entity : list) {
            Long cid = entity.getId();
            AuthorEntity authorEntity = authorService.getById(entity.getAuthorId());

            List<ReplyEntity> reply_list = replyService.list(new QueryWrapper<ReplyEntity>().eq("comment_id", cid));
            for (ReplyEntity replyEntity : reply_list) {
                AuthorEntity authorEntity_r = authorService.getById(replyEntity.getAuthorId());
                replyEntity.setAuthor(authorEntity_r);
            }
//            List<Object> reply_comments = new ArrayList<>(reply_list);

//            entity.setReplys(reply_comments);
            entity.setReplys(reply_list);
            entity.setAuthor(authorEntity);
        }
        List<Object> comments = new ArrayList<>(list);
        articleService.saveOrUpdate(article);
        article.setComments(comments);

        return R.ok().put("article", article);
    }

    /**
     * 新增记录
     */
    @PostMapping("/save")
    @RequiresPermissions("arct:article:save")
    @ApiOperation(value = "新增记录")
    public R save(@RequestBody ArticleEntity article){
        List<ArticleEntity> lists = articleService.list();

        Long id = lists.get(lists.size() - 1).getId() + 1;
        Long aid = article.getAuthorId();

        AuthorEntity authorEntity = authorService.getById(aid);
        String articleIds = authorEntity.getArticleIds();
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(articleIds.split(",")));
        list.add(id.toString());
        String list_str = StringUtils.join(list,",");
        authorEntity.setArticleIds(list_str);
        authorService.saveOrUpdate(authorEntity);

		articleService.save(article);

        return R.ok();
    }

    /**
     * 根据id修改记录
     */
    @PostMapping("/update")
    @RequiresPermissions("arct:article:update")
    @ApiOperation(value = "根据id修改信息")
    public R update(@RequestBody ArticleEntity article){
		articleService.updateById(article);

        return R.ok();
    }

    /**
     * 点赞、收藏文章或者关注作者
     */
    @PostMapping("/save/option")
    @RequiresPermissions("arct:article:save")
    public R data(HttpServletRequest request) {
        articleService.saveOption(request);

        return R.ok();
    }

    /**
     * 根据id删除记录
     */
    @PostMapping("/delete")
    @RequiresPermissions("arct:article:delete")
    @ApiOperation(value = "根据id删除记录(非逻辑删除，建议不使用)")
    public R delete(@RequestBody Long[] ids){
		articleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
