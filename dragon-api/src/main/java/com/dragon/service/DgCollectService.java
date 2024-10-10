package com.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.domain.DgCollect;
import com.dragon.domain.DgCollectExample;
import com.dragon.mapper.DgCollectMapper;
import com.dragon.req.collect.DgCollectGetReq;
import com.dragon.req.collect.DgCollectQueryReq;
import com.dragon.req.collect.DgCollectSaveReq;
import com.dragon.vo.collect.DgCollectVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaoshen
 */
public interface DgCollectService extends IService<DgCollect> {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page<DgCollect> page(DgCollectQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgCollect> all();

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgCollectVo get(DgCollectGetReq req);

    /**
     * 新增收藏
     *
     * @param req
     * @return
     */
    public int create(DgCollectSaveReq req);

    /**
     * 查询是否收藏
     *
     * @param req
     * @return
     */
    public boolean isCollect(DgCollectSaveReq req);


    /**
     * 取消收藏
     *
     * @param req
     * @return
     */
    public int cancel(DgCollectGetReq req);
}
