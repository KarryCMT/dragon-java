package com.dragon.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.domain.DgComment;
import com.dragon.domain.DgDraft;
import com.dragon.domain.DgDraftExample;
import com.dragon.mapper.DgCommentMapper;
import com.dragon.mapper.DgDraftMapper;
import com.dragon.req.draft.DgDraftGetReq;
import com.dragon.req.draft.DgDraftQueryReq;
import com.dragon.req.draft.DgDraftSaveReq;
import com.dragon.service.DgCommentService;
import com.dragon.service.DgDraftService;
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
@Service
@Slf4j
@Scope("prototype")
public class DgDraftServiceImpl extends ServiceImpl<DgDraftMapper, DgDraft> implements DgDraftService {

    @Autowired
    private DgDraftMapper DgDraftMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgDraftQueryReq req) {
//        DgDraftExample dgDraftExample = new DgDraftExample();
//        DgDraftExample.Criteria criteria = dgDraftExample.createCriteria();
//        PageHelper.startPage(req.getPageNum(), req.getPageSize());
//        //  查询 用户ID
//        if (ObjectUtil.isNotNull(req.getUserId())) {
//            criteria.andUserIdEqualTo(req.getUserId());
//        }
//        //  查询状态
//        if (ObjectUtil.isNotNull(req.getStatus())) {
//            criteria.andStatusEqualTo(req.getStatus());
//        }
//        criteria.andFlagEqualTo(1);
//        List<DgDraft> list = DgDraftMapper.selectByExample(dgDraftExample);
//        PageInfo<DgDraft> pageInfo = new PageInfo<>(list);
//        pageInfo.setPageNum(req.getPageNum());
//        pageInfo.setPageSize(req.getPageSize());
//        return pageInfo;
        return null;

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
