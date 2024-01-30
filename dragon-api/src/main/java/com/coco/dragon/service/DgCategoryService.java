package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.coco.dragon.domain.DgCategory;
import com.coco.dragon.domain.DgCategoryExample;
import com.coco.dragon.mapper.DgCategoryMapper;
import com.coco.dragon.req.category.DgCategoryGetReq;
import com.coco.dragon.req.category.DgCategoryQueryReq;
import com.coco.dragon.req.category.DgCategorySaveReq;
import com.coco.dragon.resp.category.DgCategoryResp;
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
public class DgCategoryService {

    @Resource
    private DgCategoryMapper dgCategoryMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public PageInfo page(DgCategoryQueryReq req) {
        DgCategoryExample dgCategoryExample = new DgCategoryExample();
        DgCategoryExample.Criteria criteria = dgCategoryExample.createCriteria();
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        //  模糊查询 分类名称
        if (StringUtils.isNotBlank(req.getName())) {
            criteria.andNameLike('%' + req.getName() + '%');
        }
        //  查询状态
        if (ObjectUtil.isNotNull(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        criteria.andFlagEqualTo(1);
        List<DgCategory> list = dgCategoryMapper.selectByExample(dgCategoryExample);
        PageInfo<DgCategory> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(req.getPageNum());
        pageInfo.setPageSize(req.getPageSize());
        return pageInfo;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgCategory> all() {
        DgCategoryExample dgCategoryExample = new DgCategoryExample();
        DgCategoryExample.Criteria criteria = dgCategoryExample.createCriteria();
        criteria.andFlagEqualTo(1);
        return dgCategoryMapper.selectByExample(dgCategoryExample);

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgCategoryResp get(DgCategoryGetReq req) {
        DgCategory category = dgCategoryMapper.selectByPrimaryKey(req.getId());
        DgCategoryResp resp = BeanUtil.copyProperties(category, DgCategoryResp.class);
        return resp;
    }

    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int create(DgCategorySaveReq req) {
        DateTime now = DateTime.now();
        DgCategory category = BeanUtil.copyProperties(req, DgCategory.class);
        category.setId(SnowUtil.getSnowflakeNextId());
        category.setCreateTime(now);
        category.setUpdateTime(now);
        category.setCreatorId(1L);
        category.setUpdatorId(1L);
        category.setFlag(1);
        category.setStatus(1);
        return dgCategoryMapper.insert(category);
    }


    /**
     * 修改
     *
     * @param req
     * @return
     */
    public int update(DgCategorySaveReq req) {
        DateTime now = DateTime.now();
        DgCategoryExample dgCategoryExample = new DgCategoryExample();
        DgCategoryExample.Criteria criteria = dgCategoryExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        DgCategory category = BeanUtil.copyProperties(req, DgCategory.class);
        category.setUpdateTime(now);
        category.setUpdatorId(1L);
        return dgCategoryMapper.updateByExampleSelective(category,dgCategoryExample);
    }
    /**
     * 删除
     *
     * @param req
     * @return
     */
    public int delete(DgCategoryGetReq req) {
        DgCategoryExample dgCategoryExample = new DgCategoryExample();
        DgCategoryExample.Criteria criteria = dgCategoryExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        DgCategory category = dgCategoryMapper.selectByPrimaryKey(req.getId());
        category.setFlag(0);
        return dgCategoryMapper.updateByExampleSelective(category, dgCategoryExample);
    }
}
