package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.sys.entity.AuthorEntity;
import io.renren.modules.sys.service.AuthorService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-01-20 15:54:46
 */
@RestController
@RequestMapping("/sys/author")
@Api("用户相关接口")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("用户列表")
    @RequiresPermissions("sys:author:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("用户信息")
    @RequiresPermissions("sys:author:info")
    public R info(@PathVariable("id") Long id){
		AuthorEntity author = authorService.getById(id);

        return R.ok().put("author", author);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("新增用户")
    @RequiresPermissions("sys:author:save")
    public R save(@RequestBody AuthorEntity author){
		authorService.save(author);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改用户信息")
    @RequiresPermissions("sys:author:update")
    public R update(@RequestBody AuthorEntity author){
		authorService.updateById(author);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    @ApiOperation("删除用户")
    @RequiresPermissions("sys:author:delete")
    public R delete(@RequestBody Long[] ids){
		authorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
