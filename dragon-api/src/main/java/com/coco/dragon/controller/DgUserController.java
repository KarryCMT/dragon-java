package com.coco.dragon.controller;

import com.coco.dragon.client.UserFeignClient;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.req.user.DgUserQueryReq;
import com.coco.dragon.req.user.DgUserSaveReq;
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

    @Autowired
    private UserFeignClient dgUserService;


    @PostMapping("/get")
    public Result get(@Valid @RequestBody DgUserGetReq req) {
         Map<String,Object> map = dgUserService.getMember(req);
        return Result.success(map);
    }

    @PostMapping("/all")
    public Result findAll() {
        Map<String,Object> map = dgUserService.getMemberFindAll();
        return Result.success(map);
    }

}
