package com.coco.dragon.controller;

import com.coco.dragon.req.footMark.DgFootMarkGetReq;
import com.coco.dragon.req.footMark.DgFootMarkQueryReq;
import com.coco.dragon.req.footMark.DgFootMarkSaveReq;
import com.coco.dragon.resp.footMark.DgFootMarkResp;
import com.coco.dragon.service.DgFootMarkService;
import com.coco.dragon.service.DgFootMarkService;
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
@RequestMapping("/foot/mark")
public class DgFootMarkController {
    @Resource
    private DgFootMarkService dgFootMarkService;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgFootMarkQueryReq req) {
        PageInfo findPage = dgFootMarkService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/find/all")
    public Result all() {
        List list = dgFootMarkService.all();
        return Result.success(list);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgFootMarkGetReq req) {
        DgFootMarkResp footMarkResp = dgFootMarkService.get(req);
        return Result.success(footMarkResp);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgFootMarkSaveReq req) {
        int isCreate = dgFootMarkService.create(req);
        return Result.success(isCreate);
    }

}
