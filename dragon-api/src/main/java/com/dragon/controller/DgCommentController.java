package com.dragon.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.req.comment.DgCommentGetReq;
import com.dragon.req.comment.DgCommentQueryReq;
import com.dragon.req.comment.DgCommentSaveReq;
import com.dragon.vo.comment.DgCommentResp;
import com.dragon.service.DgCommentService;
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
@RequestMapping("/comment")
public class DgCommentController {
    @Autowired
    private DgCommentService dgCommentService;

    @PostMapping("/find/page")
    public ResponseBean page(@Validated @RequestBody DgCommentQueryReq req) {
        Page page = dgCommentService.page(req);
        List<DgCommentResp> data = BeanUtil.copyToList(page.getRecords() , DgCommentResp.class);
        return ResponseBean.success(data,page);
    }

    /**
     * 根据帖子id 查询所有评论
     * @param req
     * @return
     */
    @PostMapping("/find/all")
    public ResponseBean all(@RequestBody DgCommentGetReq req) {
        List list = dgCommentService.all(req);
        return ResponseBean.success(list);
    }

    /**
     * 根据帖子id的评论 查询所有子评论回复
     * @param req
     * @return
     */
    @PostMapping("/find/child/all")
    public ResponseBean findChildCommentAll(@RequestBody DgCommentGetReq req) {
        List list = dgCommentService.ChildAll(req);
        return ResponseBean.success(list);
    }

    @PostMapping("/get")
    public ResponseBean get(@Validated @RequestBody DgCommentGetReq req) {
        DgCommentResp commentResp = dgCommentService.get(req);
        return ResponseBean.success(commentResp);
    }

    @PostMapping("/create")
    public ResponseBean create(@Validated @RequestBody DgCommentSaveReq req) {
        int isCreate = dgCommentService.create(req);
        return ResponseBean.success(isCreate);
    }


    @PostMapping("/delete")
    public ResponseBean cancel(@Validated @RequestBody DgCommentGetReq req) {
        int isDel = dgCommentService.delete(req);
        return ResponseBean.success(isDel);
    }

}
