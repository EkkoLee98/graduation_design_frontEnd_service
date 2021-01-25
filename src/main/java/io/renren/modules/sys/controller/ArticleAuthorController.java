package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.xss.XssHttpServletRequestWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.ArticleAuthorEntity;
import io.renren.modules.sys.service.ArticleAuthorService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-01-20 15:54:46
 */
@RestController
@RequestMapping("/sys/articleauthor")
public class ArticleAuthorController {
    @Autowired
    private ArticleAuthorService articleAuthorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:articleauthor:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleAuthorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:articleauthor:info")
    public R info(@PathVariable("id") Long id){
		ArticleAuthorEntity articleAuthor = articleAuthorService.getById(id);

        return R.ok().put("articleAuthor", articleAuthor);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:articleauthor:save")
    public R save(@RequestBody ArticleAuthorEntity articleAuthor){
		articleAuthorService.save(articleAuthor);

        return R.ok();
    }

    /**
     * 保存富文本数据
     */
    @RequestMapping("/save/content")
    @RequiresPermissions("sys:articleauthor:save")
    public R data(HttpServletRequest request) {
        articleAuthorService.saveContent(request);

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:articleauthor:update")
    public R update(@RequestBody ArticleAuthorEntity articleAuthor){
		articleAuthorService.updateById(articleAuthor);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:articleauthor:delete")
    public R delete(@RequestBody Long[] ids){
		articleAuthorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
