package io.renren.modules.arct.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.arct.entity.ReplyEntity;
import io.renren.modules.arct.service.ReplyService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-02-10 15:24:43
 */
@RestController
@RequestMapping("arct/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("arct:reply:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = replyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("arct:reply:info")
    public R info(@PathVariable("id") Long id){
		ReplyEntity reply = replyService.getById(id);

        return R.ok().put("reply", reply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("arct:reply:save")
    public R save(@RequestBody ReplyEntity reply){
		replyService.save(reply);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("arct:reply:update")
    public R update(@RequestBody ReplyEntity reply){
		replyService.updateById(reply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("arct:reply:delete")
    public R delete(@RequestBody Long[] ids){
		replyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
