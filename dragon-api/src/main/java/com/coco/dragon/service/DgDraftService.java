package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.coco.dragon.domain.DgDraft;
import com.coco.dragon.domain.DgDraftExample;
import com.coco.dragon.domain.DgFollow;
import com.coco.dragon.domain.DgFollowExample;
import com.coco.dragon.mapper.DgDraftMapper;
import com.coco.dragon.mapper.DgFollowMapper;
import com.coco.dragon.req.draft.DgDraftGetReq;
import com.coco.dragon.req.draft.DgDraftQueryReq;
import com.coco.dragon.req.draft.DgDraftSaveReq;
import com.coco.dragon.resp.draft.DgDraftResp;
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
public class DgDraftService {

    @Resource
    private DgDraftMapper DgDraftMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public PageInfo page(DgDraftQueryReq req) {
        DgDraftExample dgDraftExample = new DgDraftExample();
        DgDraftExample.Criteria criteria = dgDraftExample.createCriteria();
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
        List<DgDraft> list = DgDraftMapper.selectByExample(dgDraftExample);
        PageInfo<DgDraft> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(req.getPageNum());
        pageInfo.setPageSize(req.getPageSize());
        return pageInfo;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgDraft> all() {
        DgDraftExample dgDraftExample = new DgDraftExample();
        DgDraftExample.Criteria criteria = dgDraftExample.createCriteria();
        criteria.andFlagEqualTo(1);
        return DgDraftMapper.selectByExample(dgDraftExample);

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgDraftResp get(DgDraftGetReq req) {
        DgDraft draft = DgDraftMapper.selectByPrimaryKey(req.getId());
        DgDraftResp resp = BeanUtil.copyProperties(draft, DgDraftResp.class);
        return resp;
    }

    /**
     * 新增草稿
     *
     * @param req
     * @return
     */
    public int create(DgDraftSaveReq req) {
        DgDraft draft = BeanUtil.copyProperties(req, DgDraft.class);
        return DgDraftMapper.insert(draft);
    }


    /**
     * 删除草稿
     *
     * @param req
     * @return
     */
    public int delete(DgDraftGetReq req) {
        DgDraftExample dgDraftExample = new DgDraftExample();
        DgDraftExample.Criteria criteria = dgDraftExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        criteria.andUserIdEqualTo(req.getUserId());
        DgDraft draft = DgDraftMapper.selectByPrimaryKey(req.getId());
        draft.setFlag(0);
        return DgDraftMapper.updateByExampleSelective(draft, dgDraftExample);
    }
}
