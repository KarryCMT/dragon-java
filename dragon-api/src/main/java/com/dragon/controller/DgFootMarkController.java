package com.dragon.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.req.footMark.DgFootMarkGetReq;
import com.dragon.req.footMark.DgFootMarkQueryReq;
import com.dragon.req.footMark.DgFootMarkSaveReq;
import com.dragon.vo.footMark.DgFootMarkResp;
import com.dragon.service.DgFootMarkService;
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
@RequestMapping("/foot/mark")
public class DgFootMarkController {
    @Autowired
    private DgFootMarkService dgFootMarkService;

    @PostMapping("/find/page")
    public ResponseBean page(@Validated @RequestBody DgFootMarkQueryReq req) {
        Page findPage = dgFootMarkService.page(req);
        return ResponseBean.success(findPage);
    }

    @PostMapping("/find/all")
    public ResponseBean all() {
        List list = dgFootMarkService.all();
        return ResponseBean.success(list);
    }

    @PostMapping("/get")
    public ResponseBean get(@Validated @RequestBody DgFootMarkGetReq req) {
        DgFootMarkResp footMarkResp = dgFootMarkService.get(req);
        return ResponseBean.success(footMarkResp);
    }

    @PostMapping("/create")
    public ResponseBean create(@Validated @RequestBody DgFootMarkSaveReq req) {
        int isCreate = dgFootMarkService.create(req);
        return ResponseBean.success(isCreate);
    }

}
