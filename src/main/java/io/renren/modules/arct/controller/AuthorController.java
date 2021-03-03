package io.renren.modules.arct.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.arct.entity.AuthorEntity;
import io.renren.modules.arct.service.AuthorService;
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
@RequestMapping("arct/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("arct:author:list")
    @ApiOperation(value = "查询列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = authorService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 喜欢的文章列表
     */
    @PostMapping("/list/like")
    @RequiresPermissions("arct:author:list")
    public R like_list(HttpServletRequest request) {

        return R.ok().put("authors", authorService.selectLikeList(request));
    }


    /**
     * 根据id查询信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("arct:author:info")
    @ApiOperation(value = "根据id查询信息")
    public R info(@PathVariable("id") Long id){
		AuthorEntity author = authorService.getById(id);

        return R.ok().put("author", author);
    }

    /**
     * 新增记录
     */
    @PostMapping("/save")
    @RequiresPermissions("arct:author:save")
    @ApiOperation(value = "新增记录")
    public R save(@RequestBody AuthorEntity author){
		authorService.save(author);

        return R.ok();
    }

    /**
     * 根据id修改记录
     */
    @PostMapping("/update")
    @RequiresPermissions("arct:author:update")
    @ApiOperation(value = "根据id修改信息")
    public R update(@RequestBody AuthorEntity author){
		authorService.updateById(author);

        return R.ok();
    }

    /**
     * 根据id删除记录
     */
    @PostMapping("/delete")
    @RequiresPermissions("arct:author:delete")
    @ApiOperation(value = "根据id删除记录(非逻辑删除，建议不使用)")
    public R delete(@RequestBody Long[] ids){
		authorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
