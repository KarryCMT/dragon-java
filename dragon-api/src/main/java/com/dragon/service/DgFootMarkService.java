package com.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.domain.DgFootMark;
import com.dragon.domain.DgFootMarkExample;
import com.dragon.mapper.DgFootMarkMapper;
import com.dragon.req.footMark.DgFootMarkGetReq;
import com.dragon.req.footMark.DgFootMarkQueryReq;
import com.dragon.req.footMark.DgFootMarkSaveReq;
import com.dragon.vo.footMark.DgFootMarkResp;
import com.monkey.common.bean.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaoshen
 */
public interface DgFootMarkService extends IService<DgFootMark> {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgFootMarkQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgFootMark> all();

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgFootMarkResp get(DgFootMarkGetReq req);

    /**
     * 新增足迹
     *
     * @param req
     * @return
     */
    public int create(DgFootMarkSaveReq req);
}
