package com.coco.dragon.controller;

import com.coco.dragon.req.comment.DgCommentGetReq;
import com.coco.dragon.req.comment.DgCommentQueryReq;
import com.coco.dragon.req.comment.DgCommentSaveReq;
import com.coco.dragon.resp.comment.DgCommentResp;
import com.coco.dragon.service.DgCommentService;
import com.coco.dragon.service.DgCommentService;
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
@RequestMapping("/comment")
public class DgCommentController {
    @Resource
    private DgCommentService dgCommentService;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgCommentQueryReq req) {
        PageInfo findPage = dgCommentService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/find/all")
    public Result all() {
        List list = dgCommentService.all();
        return Result.success(list);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgCommentGetReq req) {
        DgCommentResp commentResp = dgCommentService.get(req);
        return Result.success(commentResp);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgCommentSaveReq req) {
        int isCreate = dgCommentService.create(req);
        return Result.success(isCreate);
    }


    @PostMapping("/delete")
    public Result cancel(@Valid @RequestBody DgCommentGetReq req) {
        int isDel = dgCommentService.delete(req);
        return Result.success(isDel);
    }

}
