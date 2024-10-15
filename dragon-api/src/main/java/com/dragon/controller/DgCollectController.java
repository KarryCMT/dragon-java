package com.dragon.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dragon.domain.DgCollect;
import com.dragon.req.collect.DgCollectGetReq;
import com.dragon.req.collect.DgCollectQueryReq;
import com.dragon.req.collect.DgCollectSaveReq;
import com.dragon.vo.collect.DgCollectVo;
import com.dragon.service.DgCollectService;
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
@RequestMapping("/collect")
public class DgCollectController {
    @Autowired
    private DgCollectService dgCollectService;

    @PostMapping("/find/page")
    public ResponseBean page(@Validated @RequestBody DgCollectQueryReq req) {
        Page<DgCollect> page = dgCollectService.page(req);
        List<DgCollectVo> data = BeanUtil.copyToList(page.getRecords(),DgCollectVo.class);
        return ResponseBean.success(data,page);
    }

    @PostMapping("/find/all")
    public ResponseBean all() {
        List list = dgCollectService.all();
        return ResponseBean.success(list);
    }

    @PostMapping("/get")
    public ResponseBean get(@Validated @RequestBody DgCollectGetReq req) {
        DgCollectVo collectResp = dgCollectService.get(req);
        return ResponseBean.success(collectResp);
    }

    @PostMapping("/create")
    public ResponseBean create(@Validated @RequestBody DgCollectSaveReq req) {
        int isCreate = dgCollectService.create(req);
        return ResponseBean.success(isCreate);
    }


    @PostMapping("/cancel")
    public ResponseBean cancel(@Validated @RequestBody DgCollectGetReq req) {
        int isDel = dgCollectService.cancel(req);
        return ResponseBean.success(isDel);
    }

}
