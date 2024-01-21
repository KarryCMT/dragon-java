package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.coco.dragon.domain.DgFollow;
import com.coco.dragon.domain.DgFollowExample;
import com.coco.dragon.mapper.DgFollowMapper;
import com.coco.dragon.req.follow.DgFollowGetReq;
import com.coco.dragon.req.follow.DgFollowQueryReq;
import com.coco.dragon.req.follow.DgFollowSaveReq;
import com.coco.dragon.resp.follow.DgFollowResp;
import com.coco.rabbit.common.exception.RabbitException;
import com.coco.rabbit.common.util.SnowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaoshen
 */
@Service
@Slf4j
@Scope("prototype")
public class DgFollowService {

    @Resource
    private DgFollowMapper dgFollowMapper;



    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public PageInfo page(DgFollowQueryReq req) {
        DgFollowExample dgFollowExample = new DgFollowExample();
        DgFollowExample.Criteria criteria = dgFollowExample.createCriteria();
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        //  查询 用户ID
        if (ObjectUtil.isNotNull(req.getUserId())) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        //  查询状态
        if (ObjectUtil.isNotNull(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        criteria.andFlagEqualTo(1);
        List<DgFollow> list = dgFollowMapper.selectByExample(dgFollowExample);
        PageInfo<DgFollow> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(req.getPageNum());
        pageInfo.setPageSize(req.getPageSize());
        return pageInfo;
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
        List<DgFollow> list= dgFollowMapper.selectByExample(dgFollowExample);
        if (list.size() == 1){
            throw new RabbitException("该用户已关注");
        }
        if (req.getUserId().equals(req.getFollowedId())){
            throw new RabbitException("不能关注该自己");
        }
        DgFollow follow = BeanUtil.copyProperties(req, DgFollow.class);
        follow.setId(SnowUtil.getSnowflakeNextId());
        follow.setCreateTime(now);
        follow.setUpdateTime(now);
        follow.setCreatorId(1L);
        follow.setUpdatorId(1L);
        follow.setFlag(1);
        follow.setStatus(1);
        return dgFollowMapper.insert(follow);
    }


    /**
     * 取消关注
     *
     * @param req
     * @return
     */
    public int cancel(DgFollowGetReq req) {
        DgFollowExample dgFollowExample = new DgFollowExample();
        DgFollowExample.Criteria criteria = dgFollowExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        criteria.andUserIdEqualTo(req.getUserId());
        DgFollow collect = dgFollowMapper.selectByPrimaryKey(req.getId());
        collect.setFlag(0);
        return dgFollowMapper.updateByExampleSelective(collect, dgFollowExample);
    }
}
