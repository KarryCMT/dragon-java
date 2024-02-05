package com.coco.dragon.controller;

import com.coco.dragon.req.topic.DgTopicGetReq;
import com.coco.dragon.req.topic.DgTopicQueryReq;
import com.coco.dragon.req.topic.DgTopicSaveReq;
import com.coco.dragon.resp.topic.DgTopicResp;
import com.coco.dragon.service.DgTopicService;
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
@RequestMapping("/topic")
public class DgTopicController {
    @Resource
    private DgTopicService dgTopicService;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgTopicQueryReq req) {
        PageInfo findPage = dgTopicService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/find/all")
    public Result all() {
        List list = dgTopicService.all();
        return Result.success(list);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgTopicGetReq req) {
        DgTopicResp topicResp = dgTopicService.get(req);
        return Result.success(topicResp);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgTopicSaveReq req) {
        int isCreate = dgTopicService.create(req);
        return Result.success(isCreate);
    }

    @PostMapping("/update")
    public Result update(@Valid @RequestBody DgTopicSaveReq req) {
        int isCreate = dgTopicService.update(req);
        return Result.success(isCreate);
    }

    @PostMapping("/delete")
    public Result delete(@Valid @RequestBody DgTopicGetReq req) {
        int isDel = dgTopicService.delete(req);
        return Result.success(isDel);
    }

}
