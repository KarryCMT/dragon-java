package com.coco.dragon.controller;

import com.coco.dragon.client.UserFeignClient;
import com.coco.dragon.req.member.MemberReq;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.req.user.DgUserQueryReq;
import com.coco.dragon.req.user.DgUserSaveReq;
import com.coco.dragon.resp.user.SsMember;
import com.coco.rabbit.common.resp.Result;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Result get(@Valid @RequestBody MemberReq req) {
         SsMember map = dgUserService.getMember(req);
        return Result.success(map);
    }

    @PostMapping("/all")
    public Result findAll() {
         List<SsMember> list = dgUserService.getMemberFindAll();
        return Result.success(list);
    }

}
