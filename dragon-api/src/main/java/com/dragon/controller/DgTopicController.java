package com.dragon.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.req.topic.DgTopicGetReq;
import com.dragon.req.topic.DgTopicQueryReq;
import com.dragon.req.topic.DgTopicSaveReq;
import com.dragon.vo.topic.DgTopicResp;
import com.dragon.service.DgTopicService;
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
@RequestMapping("/topic")
public class DgTopicController {
    @Autowired
    private DgTopicService dgTopicService;

    @PostMapping("/find/page")
    public ResponseBean page(@Validated @RequestBody DgTopicQueryReq req) {
        Page findPage = dgTopicService.page(req);
        return ResponseBean.success(findPage);
    }

    @PostMapping("/find/all")
    public ResponseBean all() {
        List list = dgTopicService.all();
        return ResponseBean.success(list);
    }

    @PostMapping("/get")
    public ResponseBean get(@Validated @RequestBody DgTopicGetReq req) {
        DgTopicResp topicResp = dgTopicService.get(req);
        return ResponseBean.success(topicResp);
    }

    @PostMapping("/create")
    public ResponseBean create(@Validated @RequestBody DgTopicSaveReq req) {
        int isCreate = dgTopicService.create(req);
        return ResponseBean.success(isCreate);
    }

    @PostMapping("/update")
    public ResponseBean update(@Validated @RequestBody DgTopicSaveReq req) {
        int isCreate = dgTopicService.update(req);
        return ResponseBean.success(isCreate);
    }

    @PostMapping("/delete")
    public ResponseBean delete(@Validated @RequestBody DgTopicGetReq req) {
        int isDel = dgTopicService.delete(req);
        return ResponseBean.success(isDel);
    }

}
