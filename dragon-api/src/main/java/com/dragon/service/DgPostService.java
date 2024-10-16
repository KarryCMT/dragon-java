package com.dragon.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.domain.DgPost;
import com.dragon.req.post.DgPostDraftReq;
import com.dragon.req.post.DgPostGetReq;
import com.dragon.req.post.DgPostQueryReq;
import com.dragon.req.post.DgPostSaveReq;
import com.dragon.vo.post.DgPostResp;

import java.util.List;

/**
 * @author liaoshen
 */
public interface DgPostService extends IService<DgPost> {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgPostQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgPost> all();

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgPostResp get(DgPostGetReq req);

    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int create(DgPostSaveReq req);


    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int draft(DgPostDraftReq req);


    /**
     * 修改
     *
     * @param req
     * @return
     */
    public int update(DgPostSaveReq req);

    /**
     * 删除
     *
     * @param req
     * @return
     */
    public int delete(DgPostGetReq req);
}
