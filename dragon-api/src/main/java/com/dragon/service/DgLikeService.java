package com.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface DgLikeService extends IService<DgLike> {

    /**
     * 获取帖子所有点赞数
     *
     * @return
     */
    public long all(DgLikeGetReq req);

    /**
     * 查询
     *
     * @param req
     * @return
     */
    public boolean get(DgLikeSaveReq req);

    /**
     * 查询是否点赞
     *
     * @param req
     * @return
     */
    public boolean isLike(DgLikeSaveReq req);


    /**
     * 新增点赞
     *
     * @param req
     * @return
     */
    public int create(DgLikeSaveReq req);


    /**
     * 取消点赞
     *
     * @param req
     * @return
     */
    public int update(DgLikeSaveReq req);
}
