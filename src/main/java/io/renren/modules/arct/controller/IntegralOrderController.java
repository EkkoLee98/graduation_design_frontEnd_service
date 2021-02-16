package io.renren.modules.arct.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.modules.arct.entity.AuthorEntity;
import io.renren.modules.arct.service.AuthorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.arct.entity.IntegralOrderEntity;
import io.renren.modules.arct.service.IntegralOrderService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-16 01:51:00
 */
@RestController
@RequestMapping("arct/integralorder")
public class IntegralOrderController {
    @Autowired
    private IntegralOrderService integralOrderService;
    @Autowired
    private AuthorService authorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("arct:integralorder:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = integralOrderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("arct:integralorder:info")
    public R info(@PathVariable("id") Integer id){
		IntegralOrderEntity integralOrder = integralOrderService.getById(id);

        return R.ok().put("integralOrder", integralOrder);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("arct:integralorder:save")
    public R save(@RequestBody IntegralOrderEntity integralOrder){
        Long id = integralOrder.getAuthorId();
        AuthorEntity authorEntity = authorService.getById(id);
        Long integral = authorEntity.getIntegralCount();
        authorEntity.setIntegralCount(integral - integralOrder.getIntegral());
        authorService.saveOrUpdate(authorEntity);
		integralOrderService.save(integralOrder);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("arct:integralorder:update")
    public R update(@RequestBody IntegralOrderEntity integralOrder){
		integralOrderService.updateById(integralOrder);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("arct:integralorder:delete")
    public R delete(@RequestBody Integer[] ids){
		integralOrderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
