package com.coco.dragon.controller;

import com.coco.dragon.client.UserFeignClient;
import com.coco.dragon.domain.DgLike;
import com.coco.dragon.req.like.DgLikeGetReq;
import com.coco.dragon.req.like.DgLikeQueryReq;
import com.coco.dragon.req.like.DgLikeSaveReq;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.service.DgLikeService;
import com.coco.rabbit.common.resp.Result;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author liaoshen
 */
@RestController
@RequestMapping("/like")
public class DgLikeController {

    @Resource
    private DgLikeService dgLikeService;


    @PostMapping("/operate")
    public Result create(@Valid @RequestBody DgLikeSaveReq req) {
        boolean isLike = dgLikeService.get(req);
        int isOperate;
        if (isLike){
//            如果存在，就取消
            isOperate = dgLikeService.update(req);
        }else {
//            如果不存在，就新增
            isOperate = dgLikeService.create(req);
        }
        return Result.success(isOperate);
    }

}
