package com.coco.dragon.client;

import cn.hutool.http.server.HttpServerRequest;
import com.coco.dragon.req.user.DgUserGetReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author liaoshen
 */
@Component
@FeignClient(name = "system-service",path = "",url = "http://localhost:8001/api/v1")
public interface UserFeignClient {

    /**
     *
     * @param req
     * @return
     */
    @PostMapping("/rabbit/system/member/get")
    Map<String,Object> getMember(DgUserGetReq req);

    /**
     *
     * @param req
     * @return
     */
    @PostMapping("/system/member/find/all")
    Map<String,Object> getMemberFindAll(DgUserGetReq req);
}
