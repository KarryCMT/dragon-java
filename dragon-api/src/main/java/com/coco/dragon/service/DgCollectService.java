package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.coco.dragon.domain.DgCategory;
import com.coco.dragon.domain.DgCategoryExample;
import com.coco.dragon.domain.DgCollect;
import com.coco.dragon.domain.DgCollectExample;
import com.coco.dragon.exception.DragonException;
import com.coco.dragon.exception.DragonExceptionEnum;
import com.coco.dragon.mapper.DgCategoryMapper;
import com.coco.dragon.mapper.DgCollectMapper;
import com.coco.dragon.req.category.DgCategoryGetReq;
import com.coco.dragon.req.category.DgCategoryQueryReq;
import com.coco.dragon.req.category.DgCategorySaveReq;
import com.coco.dragon.req.collect.DgCollectGetReq;
import com.coco.dragon.req.collect.DgCollectQueryReq;
import com.coco.dragon.req.collect.DgCollectSaveReq;
import com.coco.dragon.resp.category.DgCategoryResp;
import com.coco.dragon.resp.collect.DgCollectResp;
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
public class DgCollectService {

    @Resource
    private DgCollectMapper dgCollectMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public PageInfo page(DgCollectQueryReq req) {
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteria = dgCollectExample.createCriteria();
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
        List<DgCollect> list = dgCollectMapper.selectByExample(dgCollectExample);
        PageInfo<DgCollect> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(req.getPageNum());
        pageInfo.setPageSize(req.getPageSize());
        return pageInfo;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgCollect> all() {
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteria = dgCollectExample.createCriteria();
        criteria.andFlagEqualTo(1);
        return dgCollectMapper.selectByExample(dgCollectExample);

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgCollectResp get(DgCollectGetReq req) {
        DgCollect collect = dgCollectMapper.selectByPrimaryKey(req.getId());
        DgCollectResp resp = BeanUtil.copyProperties(collect, DgCollectResp.class);
        return resp;
    }

    /**
     * 新增收藏
     *
     * @param req
     * @return
     */
    public int create(DgCollectSaveReq req) {
        DateTime now = DateTime.now();
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteria = dgCollectExample.createCriteria();
        criteria.andPostIdEqualTo(req.getPostId());
        criteria.andUserIdEqualTo(req.getUserId());
        List<DgCollect> list= dgCollectMapper.selectByExample(dgCollectExample);
        if (list.size() == 1){
            throw new RabbitException("该帖子已收藏");
        }
        DgCollect collect = BeanUtil.copyProperties(req, DgCollect.class);
        collect.setId(SnowUtil.getSnowflakeNextId());
        collect.setCreateTime(now);
        collect.setUpdateTime(now);
        collect.setCreatorId(1L);
        collect.setUpdatorId(1L);
        collect.setFlag(1);
        collect.setStatus(1);
        return dgCollectMapper.insert(collect);
    }


    /**
     * 取消收藏
     *
     * @param req
     * @return
     */
    public int cancel(DgCollectGetReq req) {
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteria = dgCollectExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        criteria.andUserIdEqualTo(req.getUserId());
        DgCollect collect = dgCollectMapper.selectByPrimaryKey(req.getId());
        collect.setFlag(0);
        return dgCollectMapper.updateByExampleSelective(collect, dgCollectExample);
    }
}
