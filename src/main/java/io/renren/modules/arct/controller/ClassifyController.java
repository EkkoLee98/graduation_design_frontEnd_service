package io.renren.modules.arct.controller;

import java.util.Arrays;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.arct.entity.ClassifyEntity;
import io.renren.modules.arct.service.ClassifyService;
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
@RequestMapping("arct/classify")
public class ClassifyController {
    @Autowired
    private ClassifyService classifyService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("arct:classify:list")
    @ApiOperation(value = "查询列表")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = classifyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 根据id查询信息
     */
    @GetMapping("/info/{classifyId}")
    @RequiresPermissions("arct:classify:info")
    @ApiOperation(value = "根据id查询信息")
    public R info(@PathVariable("classifyId") Integer classifyId){
		ClassifyEntity classify = classifyService.getById(classifyId);

        return R.ok().put("classify", classify);
    }

    /**
     * 新增记录
     */
    @PostMapping("/save")
    @RequiresPermissions("arct:classify:save")
    @ApiOperation(value = "新增记录")
    public R save(@RequestBody ClassifyEntity classify){
		classifyService.save(classify);

        return R.ok();
    }

    /**
     * 根据id修改记录
     */
    @PostMapping("/update")
    @RequiresPermissions("arct:classify:update")
    @ApiOperation(value = "根据id修改信息")
    public R update(@RequestBody ClassifyEntity classify){
		classifyService.updateById(classify);

        return R.ok();
    }

    /**
     * 根据id删除记录
     */
    @PostMapping("/delete")
    @RequiresPermissions("arct:classify:delete")
    @ApiOperation(value = "根据id删除记录(非逻辑删除，建议不使用)")
    public R delete(@RequestBody Integer[] classifyIds){
		classifyService.removeByIds(Arrays.asList(classifyIds));

        return R.ok();
    }

}
