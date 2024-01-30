package com.coco.dragon.controller;

import com.coco.dragon.client.UserFeignClient;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.req.user.DgUserQueryReq;
import com.coco.dragon.req.user.DgUserSaveReq;
import com.coco.dragon.service.DgUserService;
import com.coco.rabbit.common.resp.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author liaoshen
 */
@RestController
@RequestMapping("/user")
public class DgUserController {
    @Resource
    private DgUserService dgUserService;

    @Autowired
    private UserFeignClient userFeignClient;

    @PostMapping("/find/page")
    public Result page(@Valid @RequestBody DgUserQueryReq req) {
        PageInfo findPage = dgUserService.page(req);
        return Result.success(findPage);
    }

    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgUserGetReq req) {
//        DgUserResp user = dgUserService.get(req);
         Map<String,Object> map = userFeignClient.getMember(req);
        return Result.success(map);
    }

    @PostMapping("/create")
    public Result create(@Valid @RequestBody DgUserSaveReq req) {
        int isCreate = dgUserService.create(req);
        return Result.success(isCreate);
    }

    @PostMapping("/update")
    public Result update(@Valid @RequestBody DgUserSaveReq req) {
        int isCreate = dgUserService.update(req);
        return Result.success(isCreate);
    }

    @PostMapping("/delete")
    public Result delete(@Valid @RequestBody DgUserGetReq req) {
        int isDel = dgUserService.delete(req);
        return Result.success(isDel);
    }

}
