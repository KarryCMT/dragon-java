package com.dragon.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.domain.DgComment;
import com.dragon.req.comment.DgCommentGetReq;
import com.dragon.req.comment.DgCommentQueryReq;
import com.dragon.req.comment.DgCommentSaveReq;
import com.dragon.vo.comment.DgCommentResp;

import java.util.List;

/**
 * @author liaoshen
 */
public interface DgCommentService extends IService<DgComment> {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgCommentQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgCommentResp> all(DgCommentGetReq req);


    public List<DgCommentResp> ChildAll(DgCommentGetReq req);

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgCommentResp get(DgCommentGetReq req);

    /**
     * 新增评论
     *
     * @param req
     * @return
     */
    public int create(DgCommentSaveReq req);


    /**
     * 删除评论
     *
     * @param req
     * @return
     */
    public int delete(DgCommentGetReq req);
}
