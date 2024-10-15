package com.dragon.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.req.draft.DgDraftGetReq;
import com.dragon.req.draft.DgDraftQueryReq;
import com.dragon.vo.draft.DgDraftResp;
import com.dragon.service.DgDraftService;
import com.monkey.common.bean.ResponseBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author liaoshen
 */
@Controller
@RestController
@RequestMapping("/draft")
public class DgDraftController {
    @Autowired
    private DgDraftService dgDraftService;

    @PostMapping("/find/page")
    public ResponseBean page(@Validated @RequestBody DgDraftQueryReq req) {
        Page findPage = dgDraftService.page(req);
        return ResponseBean.success(findPage);
    }

    @PostMapping("/find/all")
    public ResponseBean all() {
        List list = dgDraftService.all();
        return ResponseBean.success(list);
    }

    @PostMapping("/get")
    public ResponseBean get(@Validated @RequestBody DgDraftGetReq req) {
        DgDraftResp draftResp = dgDraftService.get(req);
        return ResponseBean.success(draftResp);
    }

    @PostMapping("/delete")
    public ResponseBean cancel(@Validated @RequestBody DgDraftGetReq req) {
        int isDel = dgDraftService.delete(req);
        return ResponseBean.success(isDel);
    }

}
