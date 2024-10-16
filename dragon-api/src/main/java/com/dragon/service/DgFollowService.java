package com.dragon.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.domain.DgFollow;
import com.dragon.req.follow.DgFollowCancelReq;
import com.dragon.req.follow.DgFollowGetReq;
import com.dragon.req.follow.DgFollowQueryReq;
import com.dragon.req.follow.DgFollowSaveReq;
import com.dragon.vo.follow.DgFollowResp;

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
