package com.dragon.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.req.post.DgPostDraftReq;
import com.dragon.req.post.DgPostGetReq;
import com.dragon.req.post.DgPostQueryReq;
import com.dragon.req.post.DgPostSaveReq;
import com.dragon.vo.post.DgPostResp;
import com.dragon.service.DgPostService;
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
@RequestMapping("/post")
public class DgPostController {

    @Autowired
    private DgPostService dgPostService;

    /**
     * 首页 分页
     * @param req
     * @return
     */
    @PostMapping("/find/page")
    public ResponseBean page(@Validated @RequestBody DgPostQueryReq req) {
        Page findPage = dgPostService.page(req);
        return ResponseBean.success(findPage);
    }

    @PostMapping("/find/all")
    public ResponseBean all() {
        List list = dgPostService.all();
        return ResponseBean.success(list);
    }

    @PostMapping("/get")
    public ResponseBean get(@Validated @RequestBody DgPostGetReq req) {
        DgPostResp categoryResp = dgPostService.get(req);
        return ResponseBean.success(categoryResp);
    }

    @PostMapping("/create")
    public ResponseBean create(@Validated @RequestBody DgPostSaveReq req) {
        int isCreate = dgPostService.create(req);
        return ResponseBean.success(isCreate);
    }

    @PostMapping("/draft")
    public ResponseBean draft(@Validated @RequestBody DgPostDraftReq req) {
        int isCreate = dgPostService.draft(req);
        return ResponseBean.success(isCreate);
    }

    @PostMapping("/update")
    public ResponseBean update(@Validated @RequestBody DgPostSaveReq req) {
        int isCreate = dgPostService.update(req);
        return ResponseBean.success(isCreate);
    }

    @PostMapping("/delete")
    public ResponseBean delete(@Validated @RequestBody DgPostGetReq req) {
        int isDel = dgPostService.delete(req);
        return ResponseBean.success(isDel);
    }

}
