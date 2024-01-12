package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.coco.dragon.domain.DgCategory;
import com.coco.dragon.domain.DgCategoryExample;
import com.coco.dragon.domain.DgPost;
import com.coco.dragon.domain.DgPostExample;
import com.coco.dragon.mapper.DgCategoryMapper;
import com.coco.dragon.mapper.DgPostMapper;
import com.coco.dragon.req.category.DgCategoryGetReq;
import com.coco.dragon.req.category.DgCategoryQueryReq;
import com.coco.dragon.req.category.DgCategorySaveReq;
import com.coco.dragon.req.post.DgPostGetReq;
import com.coco.dragon.req.post.DgPostQueryReq;
import com.coco.dragon.req.post.DgPostSaveReq;
import com.coco.dragon.resp.category.DgCategoryResp;
import com.coco.dragon.resp.post.DgPostResp;
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
public class DgPostService {

    @Resource
    private DgPostMapper dgPostMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public PageInfo page(DgPostQueryReq req) {
        DgPostExample dgPostExample = new DgPostExample();
        DgPostExample.Criteria criteria = dgPostExample.createCriteria();
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        //  模糊查询 标题
        if (StringUtils.isNotBlank(req.getTitle())) {
            criteria.andTitleLike('%' + req.getTitle() + '%');
        }
        //  查询状态
        if (ObjectUtil.isNotNull(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        criteria.andFlagEqualTo(1);
        List<DgPost> list = dgPostMapper.selectByExample(dgPostExample);
        PageInfo<DgPost> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(req.getPageNum());
        pageInfo.setPageSize(req.getPageSize());
        return pageInfo;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgPost> all() {
        DgPostExample dgPostExample = new DgPostExample();
        DgPostExample.Criteria criteria = dgPostExample.createCriteria();
        criteria.andFlagEqualTo(1);
        return dgPostMapper.selectByExample(dgPostExample);

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgPostResp get(DgPostGetReq req) {
        DgPost post = dgPostMapper.selectByPrimaryKey(req.getId());
        return BeanUtil.copyProperties(post, DgPostResp.class);
    }

    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int create(DgPostSaveReq req) {
        DateTime now = DateTime.now();
        DgPost post = BeanUtil.copyProperties(req, DgPost.class);
        post.setId(SnowUtil.getSnowflakeNextId());
        post.setCreateTime(now);
        post.setUpdateTime(now);
        post.setCreatorId(1L);
        post.setUpdatorId(1L);
        post.setFlag(1);
        post.setStatus(1);
        return dgPostMapper.insert(post);
    }


    /**
     * 修改
     *
     * @param req
     * @return
     */
    public int update(DgPostSaveReq req) {
        DateTime now = DateTime.now();
        DgPostExample dgPostExample = new DgPostExample();
        DgPostExample.Criteria criteria = dgPostExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        DgPost post = BeanUtil.copyProperties(req, DgPost.class);
        post.setUpdateTime(now);
        post.setUpdatorId(1L);
        return dgPostMapper.updateByExampleSelective(post,dgPostExample);
    }
    /**
     * 删除
     *
     * @param req
     * @return
     */
    public int delete(DgPostGetReq req) {
        DgPostExample dgPostExample = new DgPostExample();
        DgPostExample.Criteria criteria = dgPostExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        DgPost post = dgPostMapper.selectByPrimaryKey(req.getId());
        post.setFlag(0);
        return dgPostMapper.updateByExampleSelective(post, dgPostExample);
    }
}
