package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.coco.dragon.domain.DgTopic;
import com.coco.dragon.domain.DgTopicExample;
import com.coco.dragon.mapper.DgTopicMapper;
import com.coco.dragon.mapper.DgTopicMapper;
import com.coco.dragon.req.topic.DgTopicGetReq;
import com.coco.dragon.req.topic.DgTopicQueryReq;
import com.coco.dragon.req.topic.DgTopicSaveReq;
import com.coco.dragon.resp.topic.DgTopicResp;
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
public class DgTopicService {

    @Resource
    private DgTopicMapper dgTopicMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public PageInfo page(DgTopicQueryReq req) {
        DgTopicExample dgTopicExample = new DgTopicExample();
        DgTopicExample.Criteria criteria = dgTopicExample.createCriteria();
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        //  模糊查询 分类名称
        if (StringUtils.isNotBlank(req.getName())) {
            criteria.andTopicNameLike('%' + req.getName() + '%');
        }
        //  查询状态
        if (ObjectUtil.isNotNull(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        criteria.andFlagEqualTo(1);
        List<DgTopic> list = dgTopicMapper.selectByExample(dgTopicExample);
        PageInfo<DgTopic> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(req.getPageNum());
        pageInfo.setPageSize(req.getPageSize());
        return pageInfo;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgTopic> all() {
        DgTopicExample dgTopicExample = new DgTopicExample();
        DgTopicExample.Criteria criteria = dgTopicExample.createCriteria();
        criteria.andFlagEqualTo(1);
        return dgTopicMapper.selectByExample(dgTopicExample);

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgTopicResp get(DgTopicGetReq req) {
        DgTopic topic = dgTopicMapper.selectByPrimaryKey(req.getId());
        DgTopicResp resp = BeanUtil.copyProperties(topic, DgTopicResp.class);
        return resp;
    }

    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int create(DgTopicSaveReq req) {
        DateTime now = DateTime.now();
        DgTopic topic = BeanUtil.copyProperties(req, DgTopic.class);
        topic.setId(SnowUtil.getSnowflakeNextId());
        topic.setCreateTime(now);
        topic.setUpdateTime(now);
        topic.setCreatorId(1L);
        topic.setUpdatorId(1L);
        topic.setFlag(1);
        topic.setStatus(1);
        return dgTopicMapper.insert(topic);
    }


    /**
     * 修改
     *
     * @param req
     * @return
     */
    public int update(DgTopicSaveReq req) {
        DateTime now = DateTime.now();
        DgTopicExample dgTopicExample = new DgTopicExample();
        DgTopicExample.Criteria criteria = dgTopicExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        DgTopic topic = BeanUtil.copyProperties(req, DgTopic.class);
        topic.setUpdateTime(now);
        topic.setUpdatorId(1L);
        return dgTopicMapper.updateByExampleSelective(topic,dgTopicExample);
    }
    /**
     * 删除
     *
     * @param req
     * @return
     */
    public int delete(DgTopicGetReq req) {
        DgTopicExample dgTopicExample = new DgTopicExample();
        DgTopicExample.Criteria criteria = dgTopicExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        DgTopic topic = dgTopicMapper.selectByPrimaryKey(req.getId());
        topic.setFlag(0);
        return dgTopicMapper.updateByExampleSelective(topic, dgTopicExample);
    }
}
