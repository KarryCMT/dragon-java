package com.coco.dragon.controller;

import com.coco.dragon.req.category.DgCategoryGetReq;
import com.coco.dragon.req.category.DgCategoryQueryReq;
import com.coco.dragon.req.category.DgCategorySaveReq;
import com.coco.dragon.req.post.DgPostDraftReq;
import com.coco.dragon.req.post.DgPostGetReq;
import com.coco.dragon.req.post.DgPostQueryReq;
import com.coco.dragon.req.post.DgPostSaveReq;
import com.coco.dragon.resp.category.DgCategoryResp;
import com.coco.dragon.resp.post.DgPostResp;
import com.coco.dragon.service.DgCategoryService;
import com.coco.dragon.service.DgPostService;
import com.coco.rabbit.common.resp.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liaoshen
 */
@RestController
@RequestMapping("/post  ")
public class DgPostController {

    @Autowired
    private DgPostService dgPostService;

    /**
     * 首页 分页
     * @param req
     * @return
     */
    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgPostQueryReq req) {
        PageInfo findPage = dgPostService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/find/all")
    public Result all() {
        List list = dgPostService.all();
        return Result.success(list);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgPostGetReq req) {
        DgPostResp categoryResp = dgPostService.get(req);
        return Result.success(categoryResp);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgPostSaveReq req) {
        int isCreate = dgPostService.create(req);
        return Result.success(isCreate);
    }

    @PostMapping("/draft")
    public Result draft(@Valid @RequestBody DgPostDraftReq req) {
        int isCreate = dgPostService.draft(req);
        return Result.success(isCreate);
    }

    @PostMapping("/update")
    public Result update(@Valid @RequestBody DgPostSaveReq req) {
        int isCreate = dgPostService.update(req);
        return Result.success(isCreate);
    }

    @PostMapping("/delete")
    public Result delete(@Valid @RequestBody DgPostGetReq req) {
        int isDel = dgPostService.delete(req);
        return Result.success(isDel);
    }

}
