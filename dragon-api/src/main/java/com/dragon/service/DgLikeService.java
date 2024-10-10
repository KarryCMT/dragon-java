package com.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.dragon.domain.DgLike;
import com.dragon.domain.DgLikeExample;
import com.dragon.mapper.DgLikeMapper;
import com.dragon.req.like.DgLikeGetReq;
import com.dragon.req.like.DgLikeSaveReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author liaoshen
 */
@Service
@Slf4j
@Scope("prototype")
public class DgLikeService {

    @Autowired
    private DgLikeMapper dgLikeMapper;


    /**
     * 获取帖子所有点赞数
     *
     * @return
     */
    public long all(DgLikeGetReq req) {
        DgLikeExample dgLikeExample = new DgLikeExample();
        DgLikeExample.Criteria criteria = dgLikeExample.createCriteria();
        criteria.andPostIdEqualTo(req.getPostId());
        criteria.andFlagEqualTo(1);
        return dgLikeMapper.countByExample(dgLikeExample);

    }

    /**
     * 查询
     *
     * @param req
     * @return
     */
    public boolean get(DgLikeSaveReq req) {
        DgLikeExample dgLikeExample = new DgLikeExample();
        DgLikeExample.Criteria criteria = dgLikeExample.createCriteria();
        criteria.andPostIdEqualTo(req.getPostId());
        criteria.andUserIdEqualTo(req.getUserId());
        boolean bool = dgLikeMapper.selectByExample(dgLikeExample).size() > 0;
        return bool;
    }

    /**
     * 查询是否点赞
     *
     * @param req
     * @return
     */
    public boolean isLike(DgLikeSaveReq req) {
        DgLikeExample dgLikeExample = new DgLikeExample();
        DgLikeExample.Criteria criteria = dgLikeExample.createCriteria();
        criteria.andPostIdEqualTo(req.getPostId());
        criteria.andUserIdEqualTo(req.getUserId());
        criteria.andFlagEqualTo(1);
        int len = dgLikeMapper.selectByExample(dgLikeExample).size();
        boolean bool = len == 1;
        return bool;
    }


    /**
     * 新增点赞
     *
     * @param req
     * @return
     */
    public int create(DgLikeSaveReq req) {
        DateTime now = DateTime.now();
        DgLike like = BeanUtil.copyProperties(req, DgLike.class);
        like.setPostId(req.getPostId());
        like.setUserId(req.getUserId());
        like.setCreateTime(now);
        like.setUpdateTime(now);
        like.setCreatorId(1L);
        like.setUpdatorId(1L);
        like.setFlag(1);
        like.setStatus(1);
        return dgLikeMapper.insert(like);
    }


    /**
     * 取消点赞
     *
     * @param req
     * @return
     */
    public int update(DgLikeSaveReq req) {
        DgLikeExample dgLikeExample = new DgLikeExample();
        DgLikeExample.Criteria criteria = dgLikeExample.createCriteria();
        criteria.andUserIdEqualTo(req.getUserId());
        criteria.andPostIdEqualTo(req.getPostId());
        DgLike like = dgLikeMapper.selectByExample(dgLikeExample).get(0);
        int fag = like.getFlag() == 1 ? 0 : 1;
        like.setFlag(fag);
        return dgLikeMapper.updateByExampleSelective(like, dgLikeExample);
    }
}
