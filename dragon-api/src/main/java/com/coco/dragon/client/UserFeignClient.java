package com.coco.dragon.client;

import com.coco.dragon.req.member.MemberReq;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.resp.user.SsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/**
 * @author liaoshen
 */
@Component
@FeignClient(name = "system-service",path = "",url = "http://localhost:8001/api/v1/rabbit")
public interface UserFeignClient {

    /**
     * 查询单个
     * @param req
     * @return
     */
    @PostMapping("/system/member/find/info")
    SsMember getMember(MemberReq req);

    /**
     * 查询所有用户
     * @param
     * @return
     */
    @PostMapping("/system/member/all")
    List<SsMember> getMemberFindAll();
}
