package com.dragon.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.req.follow.DgFollowCancelReq;
import com.dragon.req.follow.DgFollowGetReq;
import com.dragon.req.follow.DgFollowQueryReq;
import com.dragon.req.follow.DgFollowSaveReq;
import com.dragon.vo.follow.DgFollowResp;
import com.dragon.service.DgFollowService;
import com.monkey.common.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
    @Autowired
    private DgFollowService dgFollowService;

    @PostMapping("/find/page")
    public ResponseBean page(@Validated @RequestBody DgFollowQueryReq req) {
        Page findPage = dgFollowService.page(req);
        return ResponseBean.success(findPage);
    }

    @PostMapping("/find/all")
    public ResponseBean all() {
        List list = dgFollowService.all();
        return ResponseBean.success(list);
    }

    @PostMapping("/get")
    public ResponseBean get(@Validated @RequestBody DgFollowGetReq req) {
        DgFollowResp followResp = dgFollowService.get(req);
        return ResponseBean.success(followResp);
    }

    @PostMapping("/create")
    public ResponseBean create(@Validated @RequestBody DgFollowSaveReq req) {
        int isCreate = dgFollowService.create(req);
        return ResponseBean.success(isCreate);
    }


    @PostMapping("/cancel")
    public ResponseBean cancel(@Validated @RequestBody DgFollowCancelReq req) {
        int isDel = dgFollowService.cancel(req);
        return ResponseBean.success(isDel);
    }

}
