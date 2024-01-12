package com.coco.dragon.controller;

import com.coco.dragon.req.collect.DgCollectGetReq;
import com.coco.dragon.req.collect.DgCollectQueryReq;
import com.coco.dragon.req.collect.DgCollectSaveReq;
import com.coco.dragon.req.post.DgPostGetReq;
import com.coco.dragon.req.post.DgPostQueryReq;
import com.coco.dragon.req.post.DgPostSaveReq;
import com.coco.dragon.resp.collect.DgCollectResp;
import com.coco.dragon.resp.post.DgPostResp;
import com.coco.dragon.service.DgCollectService;
import com.coco.dragon.service.DgPostService;
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
@RequestMapping("/collect")
public class DgCollectController {
    @Resource
    private DgCollectService dgCollectService;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgCollectQueryReq req) {
        PageInfo findPage = dgCollectService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/find/all")
    public Result all() {
        List list = dgCollectService.all();
        return Result.success(list);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgCollectGetReq req) {
        DgCollectResp collectResp = dgCollectService.get(req);
        return Result.success(collectResp);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgCollectSaveReq req) {
        int isCreate = dgCollectService.create(req);
        return Result.success(isCreate);
    }


    @PostMapping("/delete")
    public Result delete(@Valid @RequestBody DgCollectGetReq req) {
        int isDel = dgCollectService.delete(req);
        return Result.success(isDel);
    }

}
