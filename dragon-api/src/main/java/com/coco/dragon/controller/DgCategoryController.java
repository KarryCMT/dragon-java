package com.coco.dragon.controller;

import com.coco.dragon.req.category.DgCategoryGetReq;
import com.coco.dragon.req.category.DgCategoryQueryReq;
import com.coco.dragon.req.category.DgCategorySaveReq;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.req.user.DgUserQueryReq;
import com.coco.dragon.req.user.DgUserSaveReq;
import com.coco.dragon.resp.category.DgCategoryResp;
import com.coco.dragon.resp.user.DgUserResp;
import com.coco.dragon.service.DgCategoryService;
import com.coco.dragon.service.DgUserService;
import com.coco.rabbit.common.resp.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liaoshen
 */
@RestController
@RequestMapping("/category")
public class DgCategoryController {
    @Resource
    private DgCategoryService dgCategoryService;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgCategoryQueryReq req) {
        PageInfo findPage = dgCategoryService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/find/all")
    public Result all() {
        List list = dgCategoryService.all();
        return Result.success(list);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgCategoryGetReq req) {
        DgCategoryResp categoryResp = dgCategoryService.get(req);
        return Result.success(categoryResp);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgCategorySaveReq req) {
        int isCreate = dgCategoryService.create(req);
        return Result.success(isCreate);
    }

    @PostMapping("/update")
    public Result update(@Valid @RequestBody DgCategorySaveReq req) {
        int isCreate = dgCategoryService.update(req);
        return Result.success(isCreate);
    }

    @PostMapping("/delete")
    public Result delete(@Valid @RequestBody DgCategoryGetReq req) {
        int isDel = dgCategoryService.delete(req);
        return Result.success(isDel);
    }

}
