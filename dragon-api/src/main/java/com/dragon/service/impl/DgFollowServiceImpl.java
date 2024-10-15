package com.dragon.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.client.UserFeignClient;
import com.dragon.domain.DgDraft;
import com.dragon.domain.DgFollow;
import com.dragon.domain.DgFollowExample;
import com.dragon.mapper.DgDraftMapper;
import com.dragon.mapper.DgFollowMapper;
import com.dragon.req.follow.DgFollowCancelReq;
import com.dragon.req.follow.DgFollowGetReq;
import com.dragon.req.follow.DgFollowQueryReq;
import com.dragon.req.follow.DgFollowSaveReq;
import com.dragon.req.member.MemberReq;
import com.dragon.service.DgDraftService;
import com.dragon.service.DgFollowService;
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
@Service
@Slf4j
@Scope("prototype")
public class DgFollowServiceImpl extends ServiceImpl<DgFollowMapper, DgFollow> implements DgFollowService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private DgFollowMapper dgFollowMapper;



    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgFollowQueryReq req) {
//        DgFollowExample dgFollowExample = new DgFollowExample();
//        DgFollowExample.Criteria criteria = dgFollowExample.createCriteria();
//        PageHelper.startPage(req.getPageNum(), req.getPageSize());
//        //  查询 用户ID
//        if (ObjectUtil.isNotNull(req.getUserId())) {
//            criteria.andUserIdEqualTo(req.getUserId());
//        }
//        //  查询状态
//        if (ObjectUtil.isNotNull(req.getStatus())) {
//            criteria.andStatusEqualTo(req.getStatus());
//        }
//        criteria.andFlagEqualTo(1);
//        List<DgFollow> list = dgFollowMapper.selectByExample(dgFollowExample);
//        List<DgFollowResp> postRespArrayList = new ArrayList<>();
//        for (DgFollow dgFollow : list) {
//            DgFollowResp dgFollowResp = new DgFollowResp();
//            MemberReq memberReq = new MemberReq();
//            memberReq.setId(dgFollow.getFollowedId());
//            //用userId去调用 接口 找到对应的用户名称存入 name
//            SsMember member = userFeignClient.getMember(memberReq);
//            dgFollowResp.setAvatar(member.getAvatar());
//            dgFollowResp.setName(member.getName());
//            dgFollowResp.setFollowedId(dgFollow.getFollowedId());
//            dgFollowResp.setUserId(dgFollow.getUserId());
//            dgFollowResp.setDesc(member.getRemark());
//            dgFollowResp.setId(dgFollow.getId());
//            postRespArrayList.add(dgFollowResp);
//        }
//        PageInfo<DgFollowResp> pageInfo = new PageInfo<>(postRespArrayList);
//        pageInfo.setPageNum(req.getPageNum());
//        pageInfo.setPageSize(req.getPageSize());
//        return pageInfo;
        return null;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgFollow> all() {
        DgFollowExample dgFollowExample = new DgFollowExample();
        DgFollowExample.Criteria criteria = dgFollowExample.createCriteria();
        criteria.andFlagEqualTo(1);
        return dgFollowMapper.selectByExample(dgFollowExample);

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgFollowResp get(DgFollowGetReq req) {
        DgFollow collect = dgFollowMapper.selectByPrimaryKey(req.getId());
        DgFollowResp resp = BeanUtil.copyProperties(collect, DgFollowResp.class);
        return resp;
    }

    /**
     * 新增关注
     *
     * @param req
     * @return
     */
    public int create(DgFollowSaveReq req) {
        DateTime now = DateTime.now();
        DgFollowExample dgFollowExample = new DgFollowExample();
        DgFollowExample.Criteria criteria = dgFollowExample.createCriteria();
        criteria.andFollowedIdEqualTo(req.getFollowedId());
        criteria.andUserIdEqualTo(req.getUserId());
        criteria.andFlagEqualTo(1);
        List<DgFollow> list= dgFollowMapper.selectByExample(dgFollowExample);
        if (list.size() == 1){
            throw new BaseRuntimeException(null,"该用户已关注");
        }
        if (req.getUserId().equals(req.getFollowedId())){
            throw new BaseRuntimeException(null,"不能关注该自己");
        }
        DgFollow follow = BeanUtil.copyProperties(req, DgFollow.class);
        follow.setCreateTime(now);
        follow.setUpdateTime(now);
        follow.setCreatorId(1L);
        follow.setUpdatorId(1L);
        follow.setFlag(1);
        follow.setStatus(1);
        return dgFollowMapper.insert(follow);
    }

    /**
     * 查询是否关注
     *
     * @param req
     * @return
     */
    public boolean isFollow(DgFollowSaveReq req) {
        DgFollowExample dgFollowExample = new DgFollowExample();
        DgFollowExample.Criteria criteria = dgFollowExample.createCriteria();
        criteria.andFollowedIdEqualTo(req.getFollowedId());
        criteria.andUserIdEqualTo(req.getUserId());
        criteria.andFlagEqualTo(1);
        int len = dgFollowMapper.selectByExample(dgFollowExample).size();
        boolean bool = len == 1;
        return bool;
    }



    /**
     * 取消关注
     *
     * @param req
     * @return
     */
    public int cancel(DgFollowCancelReq req) {
        DgFollowExample dgFollowExample = new DgFollowExample();
        DgFollowExample.Criteria criteria = dgFollowExample.createCriteria();
        criteria.andFollowedIdEqualTo(req.getFollowedId());
        criteria.andUserIdEqualTo(req.getUserId());
        criteria.andFlagEqualTo(1);
        DgFollow collect = dgFollowMapper.selectByExample(dgFollowExample).get(0);
        if (collect == null){
            throw new BaseRuntimeException(null,"没有查询到关注用户");
        }
        collect.setFlag(0);
        return dgFollowMapper.updateByExampleSelective(collect, dgFollowExample);
    }
}
