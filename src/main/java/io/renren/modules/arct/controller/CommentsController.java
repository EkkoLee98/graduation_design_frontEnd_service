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

import io.renren.modules.arct.entity.CommentsEntity;
import io.renren.modules.arct.service.CommentsService;
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
@RequestMapping("arct/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private AuthorService authorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("arct:comments:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("arct:comments:info")
    public R info(@PathVariable("id") Long id){
		CommentsEntity comments = commentsService.getById(id);

        return R.ok().put("comments", comments);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("arct:comments:save")
    public R save(@RequestBody CommentsEntity comments){
        Long aid = comments.getAuthorId();
        AuthorEntity authorEntity = authorService.getById(aid);
        Long integralCount = authorEntity.getIntegralCount();
        long tmpIntegral = integralCount + 10;
//        String saveIntegralCount = String.valueOf(tmpIntegral);
        authorEntity.setIntegralCount(tmpIntegral);
        authorService.saveOrUpdate(authorEntity);
		commentsService.save(comments);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("arct:comments:update")
    public R update(@RequestBody CommentsEntity comments){
		commentsService.updateById(comments);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("arct:comments:delete")
    public R delete(@RequestBody Long[] ids){
		commentsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
