package com.dragon.client;

import com.dragon.req.member.MemberReq;
import com.dragon.vo.user.SsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
