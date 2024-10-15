package com.dragon.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.domain.DgTopic;
import com.dragon.req.topic.DgTopicGetReq;
import com.dragon.req.topic.DgTopicQueryReq;
import com.dragon.req.topic.DgTopicSaveReq;
import com.dragon.vo.topic.DgTopicResp;

import java.util.List;

/**
 * @author liaoshen
 */
public interface DgTopicService extends IService<DgTopic> {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgTopicQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgTopic> all();

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgTopicResp get(DgTopicGetReq req);

    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int create(DgTopicSaveReq req);


    /**
     * 修改
     *
     * @param req
     * @return
     */
    public int update(DgTopicSaveReq req);
    /**
     * 删除
     *
     * @param req
     * @return
     */
    public int delete(DgTopicGetReq req);
}
