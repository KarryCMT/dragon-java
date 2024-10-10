package com.dragon.controller;

import com.dragon.req.like.DgLikeSaveReq;
import com.dragon.service.DgLikeService;
import com.monkey.common.bean.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoshen
 */
@RestController
@RequestMapping("/like")
public class DgLikeController {

    @Autowired
    private DgLikeService dgLikeService;


    @PostMapping("/operate")
    public ResponseBean create(@Validated @RequestBody DgLikeSaveReq req) {
        boolean isLike = dgLikeService.get(req);
        int isOperate;
        if (isLike){
//            如果存在，就取消
            isOperate = dgLikeService.update(req);
        }else {
//            如果不存在，就新增
            isOperate = dgLikeService.create(req);
        }
        return ResponseBean.success(isOperate);
    }

}
