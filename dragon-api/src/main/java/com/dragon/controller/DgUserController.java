package com.dragon.controller;

import com.dragon.client.UserFeignClient;
import com.dragon.req.member.MemberReq;
import com.dragon.vo.user.SsMember;
import com.monkey.common.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liaoshen
 */
@RestController
@RequestMapping("/user")
public class DgUserController {

    @Autowired
    private UserFeignClient dgUserService;


    @PostMapping("/get")
    public ResponseBean get(@Validated @RequestBody MemberReq req) {
         SsMember map = dgUserService.getMember(req);
        return ResponseBean.success(map);
    }

    @PostMapping("/all")
    public ResponseBean findAll() {
         List<SsMember> list = dgUserService.getMemberFindAll();
        return ResponseBean.success(list);
    }

}
