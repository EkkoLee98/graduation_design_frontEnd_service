package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.xss.XssHttpServletRequestWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api("文章相关接口")
public class ArticleAuthorController {
    @Autowired
    private ArticleAuthorService articleAuthorService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("文章列表")
    @RequiresPermissions("sys:articleauthor:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = articleAuthorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("文章信息")
    @RequiresPermissions("sys:articleauthor:info")
    public R info(@PathVariable("id") Long id){
		ArticleAuthorEntity articleAuthor = articleAuthorService.getById(id);

        return R.ok().put("articleAuthor", articleAuthor);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("文章用户")
    @RequiresPermissions("sys:articleauthor:save")
    public R save(@RequestBody ArticleAuthorEntity articleAuthor){
		articleAuthorService.save(articleAuthor);

        return R.ok();
    }

    /**
     * 保存富文本数据
     */
    @PostMapping("/save/content")
    @ApiOperation("保存富文本数据")
    @RequiresPermissions("sys:articleauthor:save")
    public R data(HttpServletRequest request) {
        articleAuthorService.saveContent(request);

        return R.ok();
    }


    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改文章信息")
    @RequiresPermissions("sys:articleauthor:update")
    public R update(@RequestBody ArticleAuthorEntity articleAuthor){
		articleAuthorService.updateById(articleAuthor);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除文章")
    @RequiresPermissions("sys:articleauthor:delete")
    public R delete(@RequestBody Long[] ids){
		articleAuthorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
