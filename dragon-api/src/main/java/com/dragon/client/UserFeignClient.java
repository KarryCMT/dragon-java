package com.dragon.client;

import com.dragon.req.member.MemberReq;
import com.dragon.vo.user.RbMember;
import com.monkey.common.bean.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @author liaoshen
 */
@Component
@FeignClient(name = "rabbit-system",path = "/api/v1/rabbit",url = "http://localhost:8001")
public interface UserFeignClient {

    /**
     * 查询单个
     * @param req
     * @return
     */
    @PostMapping("/system/member/find/info")
    RbMember getMember(MemberReq req);

    /**
     * 查询所有用户
     * @param
     * @return
     */
    @PostMapping("/system/member/all")
    List<RbMember> getMemberFindAll();

    /**
     * 查询所有用户
     * @param
     * @return
     */
    @PostMapping(value = "/member/find/list/by/ids", consumes = "application/json")
    ResponseBean<Map<String, RbMember>> getMemberByIds(@RequestBody List<Long> ids);
}
