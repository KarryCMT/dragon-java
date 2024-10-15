package com.dragon.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.domain.DgFollow;
import com.dragon.domain.DgFootMark;
import com.dragon.domain.DgFootMarkExample;
import com.dragon.mapper.DgFollowMapper;
import com.dragon.mapper.DgFootMarkMapper;
import com.dragon.req.footMark.DgFootMarkGetReq;
import com.dragon.req.footMark.DgFootMarkQueryReq;
import com.dragon.req.footMark.DgFootMarkSaveReq;
import com.dragon.service.DgFollowService;
import com.dragon.service.DgFootMarkService;
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
@Service
@Slf4j
@Scope("prototype")
public class DgFootMarkServiceImpl extends ServiceImpl<DgFootMarkMapper, DgFootMark> implements DgFootMarkService {

    @Autowired
    private DgFootMarkMapper dgFootMarkMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgFootMarkQueryReq req) {
//        DgFootMarkExample dgFootMarkExample = new DgFootMarkExample();
//        DgFootMarkExample.Criteria criteria = dgFootMarkExample.createCriteria();
//        PageHelper.startPage(req.getPageNum(), req.getPageSize());
//        //  查询 用户ID
//        if (ObjectUtil.isNotNull(req.getUserId())) {
//            criteria.andUserIdEqualTo(req.getUserId());
//        }
//        criteria.andCreateTimeBetween(req.getBeginTime(),req.getEndTime());
//        criteria.andFlagEqualTo(1);
//        List<DgFootMark> list = dgFootMarkMapper.selectByExample(dgFootMarkExample);
//        PageInfo<DgFootMark> pageInfo = new PageInfo<>(list);
//        pageInfo.setPageNum(req.getPageNum());
//        pageInfo.setPageSize(req.getPageSize());
//        return SpageInfo;
        return null;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgFootMark> all() {
        DgFootMarkExample dgFootMarkExample = new DgFootMarkExample();
        DgFootMarkExample.Criteria criteria = dgFootMarkExample.createCriteria();
        criteria.andFlagEqualTo(1);
        return dgFootMarkMapper.selectByExample(dgFootMarkExample);

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgFootMarkResp get(DgFootMarkGetReq req) {
        DgFootMark footMark = dgFootMarkMapper.selectByPrimaryKey(req.getId());
        DgFootMarkResp resp = BeanUtil.copyProperties(footMark, DgFootMarkResp.class);
        return resp;
    }

    /**
     * 新增足迹
     *
     * @param req
     * @return
     */
    public int create(DgFootMarkSaveReq req) {
        DateTime now = DateTime.now();
        DgFootMarkExample dgFootMarkExample = new DgFootMarkExample();
        DgFootMarkExample.Criteria criteria = dgFootMarkExample.createCriteria();
        criteria.andUserIdEqualTo(req.getUserId());
        criteria.andPostIdEqualTo(req.getPostId());
        List<DgFootMark> list= dgFootMarkMapper.selectByExample(dgFootMarkExample);
        if (list.size() == 1){
            return 1;
        }
        DgFootMark footMark = BeanUtil.copyProperties(req, DgFootMark.class);
        footMark.setCreateTime(now);
        footMark.setUpdateTime(now);
        footMark.setCreatorId(req.getUserId());
        footMark.setUpdatorId(req.getUserId());
        footMark.setFlag(1);
        return dgFootMarkMapper.insert(footMark);
    }
}
