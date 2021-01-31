package io.renren.modules.arct.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.arct.entity.ArticleEntity;
import io.renren.modules.arct.service.ArticleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



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
    @Autowired
    private ArticleService articleService;

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
     * 根据id查询信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("arct:article:info")
    @ApiOperation(value = "根据id查询信息")
    public R info(@PathVariable("id") Long id){
		ArticleEntity article = articleService.getById(id);

        return R.ok().put("article", article);
    }

    /**
     * 新增记录
     */
    @PostMapping("/save")
    @RequiresPermissions("arct:article:save")
    @ApiOperation(value = "新增记录")
    public R save(@RequestBody ArticleEntity article){
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
