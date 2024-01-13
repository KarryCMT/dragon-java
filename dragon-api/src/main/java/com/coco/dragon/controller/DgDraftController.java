package com.coco.dragon.controller;

import com.coco.dragon.req.draft.DgDraftGetReq;
import com.coco.dragon.req.draft.DgDraftQueryReq;
import com.coco.dragon.req.draft.DgDraftSaveReq;
import com.coco.dragon.resp.draft.DgDraftResp;
import com.coco.dragon.service.DgDraftService;
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
@RequestMapping("/draft")
public class DgDraftController {
    @Resource
    private DgDraftService dgDraftService;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgDraftQueryReq req) {
        PageInfo findPage = dgDraftService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/find/all")
    public Result all() {
        List list = dgDraftService.all();
        return Result.success(list);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgDraftGetReq req) {
        DgDraftResp draftResp = dgDraftService.get(req);
        return Result.success(draftResp);
    }

    @PostMapping("/delete")
    public Result cancel(@Valid @RequestBody DgDraftGetReq req) {
        int isDel = dgDraftService.delete(req);
        return Result.success(isDel);
    }

}
