package com.coco.dragon.controller;

import com.coco.dragon.req.collect.DgCollectGetReq;
import com.coco.dragon.req.collect.DgCollectQueryReq;
import com.coco.dragon.req.collect.DgCollectSaveReq;
import com.coco.dragon.req.follow.DgFollowGetReq;
import com.coco.dragon.req.follow.DgFollowQueryReq;
import com.coco.dragon.req.follow.DgFollowSaveReq;
import com.coco.dragon.resp.collect.DgCollectResp;
import com.coco.dragon.resp.follow.DgFollowResp;
import com.coco.dragon.service.DgCollectService;
import com.coco.dragon.service.DgFollowService;
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
@RequestMapping("/follow")
public class DgFollowController {
    @Resource
    private DgFollowService dgFollowService;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgFollowQueryReq req) {
        PageInfo findPage = dgFollowService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/find/all")
    public Result all() {
        List list = dgFollowService.all();
        return Result.success(list);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgFollowGetReq req) {
        DgFollowResp followResp = dgFollowService.get(req);
        return Result.success(followResp);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgFollowSaveReq req) {
        int isCreate = dgFollowService.create(req);
        return Result.success(isCreate);
    }


    @PostMapping("/cancel")
    public Result cancel(@Valid @RequestBody DgFollowGetReq req) {
        int isDel = dgFollowService.cancel(req);
        return Result.success(isDel);
    }

}
