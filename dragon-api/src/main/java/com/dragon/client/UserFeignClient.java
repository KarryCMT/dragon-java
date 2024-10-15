package com.dragon.client;

import com.dragon.client.config.Interceptor;
import com.dragon.req.member.MemberReq;
import com.dragon.vo.user.SsMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author liaoshen
 */
@Component
@FeignClient(name = "rabbit-system",path = "/api/v1/rabbit",configuration = Interceptor.class,url = "http://localhost:8001")
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

    /**
     * 查询所有用户
     * @param
     * @return
     */
    @PostMapping("/member/find/list/by/ids")
    Map<Long,SsMember> getMemberByIds(@RequestBody List<Long> ids);
}
