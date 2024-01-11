package com.coco.dragon.controller;

import com.coco.dragon.domain.DgUser;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.req.user.DgUserQueryReq;
import com.coco.dragon.req.user.DgUserSaveReq;
import com.coco.dragon.resp.DgUserResp;
import com.coco.dragon.service.DgUserService;
import com.coco.rabbit.common.resp.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * @author liaoshen
 */
@RestController
@RequestMapping("/user")
public class DgUserController {
    @Resource
    private DgUserService dgUserService;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgUserQueryReq req) {
        PageInfo findPage = dgUserService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgUserGetReq req) {
        DgUserResp user = dgUserService.get(req);
        return Result.success(user);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgUserSaveReq req) {
        int isCreate = dgUserService.create(req);
        return Result.success(isCreate);
    }

    @PostMapping("/delete")
    public Result delete(@Valid @RequestBody DgUserGetReq req) {
        int isDel = dgUserService.delete(req);
        return Result.success(isDel);
    }

}
