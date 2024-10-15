package com.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.client.UserFeignClient;
import com.dragon.domain.DgFollow;
import com.dragon.domain.DgFollowExample;
import com.dragon.mapper.DgFollowMapper;
import com.dragon.req.follow.DgFollowCancelReq;
import com.dragon.req.follow.DgFollowGetReq;
import com.dragon.req.follow.DgFollowQueryReq;
import com.dragon.req.follow.DgFollowSaveReq;
import com.dragon.req.member.MemberReq;
import com.dragon.vo.follow.DgFollowResp;
import com.dragon.vo.user.SsMember;
import com.monkey.common.bean.PageInfo;
import com.monkey.common.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaoshen
 */
public interface DgFollowService extends IService<DgFollow> {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgFollowQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgFollow> all();

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgFollowResp get(DgFollowGetReq req);

    /**
     * 新增关注
     *
     * @param req
     * @return
     */
    public int create(DgFollowSaveReq req);

    /**
     * 查询是否关注
     *
     * @param req
     * @return
     */
    public boolean isFollow(DgFollowSaveReq req);



    /**
     * 取消关注
     *
     * @param req
     * @return
     */
    public int cancel(DgFollowCancelReq req);
}
