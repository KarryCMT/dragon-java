package com.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.domain.DgDraft;
import com.dragon.domain.DgDraftExample;
import com.dragon.mapper.DgDraftMapper;
import com.dragon.req.draft.DgDraftGetReq;
import com.dragon.req.draft.DgDraftQueryReq;
import com.dragon.req.draft.DgDraftSaveReq;
import com.dragon.vo.draft.DgDraftResp;
import com.monkey.common.bean.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaoshen
 */
public interface DgDraftService extends IService<DgDraft> {


    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgDraftQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgDraft> all();

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgDraftResp get(DgDraftGetReq req);

    /**
     * 新增草稿
     *
     * @param req
     * @return
     */
    public int create(DgDraftSaveReq req);


    /**
     * 删除草稿
     *
     * @param req
     * @return
     */
    public int delete(DgDraftGetReq req);
}
