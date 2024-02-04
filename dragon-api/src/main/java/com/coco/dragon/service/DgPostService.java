package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.coco.dragon.domain.DgPost;
import com.coco.dragon.domain.DgPostExample;
import com.coco.dragon.mapper.DgPostMapper;
import com.coco.dragon.req.draft.DgDraftSaveReq;
import com.coco.dragon.req.post.DgPostDraftReq;
import com.coco.dragon.req.post.DgPostGetReq;
import com.coco.dragon.req.post.DgPostQueryReq;
import com.coco.dragon.req.post.DgPostSaveReq;
import com.coco.dragon.resp.post.DgPostResp;
import com.coco.dragon.util.ApiClient;
import com.coco.rabbit.common.exception.RabbitException;
import com.coco.rabbit.common.util.SnowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liaoshen
 */
@Service
@Slf4j
@Scope("prototype")
public class DgPostService {

    @Resource
    private DgPostMapper dgPostMapper;

    @Resource
    private DgDraftService dgDraftService;

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
        for (DgPost dgPost : pageInfo.getList()) {
            Map<String, Object> params = new HashMap<>();
            //获取传来的信息
            params.put("id", dgPost.getUserId());
            //用userId去调用 接口 找到对应的用户名称存入 name
            DgPost call = ApiClient.call("http://127.0.0.1:8001/api/v1/rabbit/system/member/get", params, DgPost.class);
            dgPost.setName(call.getName());
        }
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
     * 新增
     *
     * @param req
     * @return
     */
    public int draft(DgPostDraftReq req) {
        DateTime now = DateTime.now();
        DgPost post = BeanUtil.copyProperties(req, DgPost.class);
        post.setId(SnowUtil.getSnowflakeNextId());
        post.setCreateTime(now);
        post.setUpdateTime(now);
        post.setCreatorId(req.getUserId());
        post.setUpdatorId(req.getUserId());
        post.setFlag(1);
        post.setStatus(0);
        // 生成草稿
        DgDraftSaveReq dgDraftSaveReq = new DgDraftSaveReq();
        dgDraftSaveReq.setId(SnowUtil.getSnowflakeNextId());
        dgDraftSaveReq.setPostId(post.getId());
        dgDraftSaveReq.setUserId(req.getUserId());
        dgDraftSaveReq.setCreateTime(now);
        dgDraftSaveReq.setCreatorId(req.getUserId());
        dgDraftSaveReq.setStatus(1);
        dgDraftSaveReq.setFlag(1);

        int isDraft = dgDraftService.create(dgDraftSaveReq);
        int isPost = dgPostMapper.insert(post);
        if (isDraft == 0 || isPost == 0) {
            throw new RabbitException("创建草稿失败");
        }
        return 1;
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
        return dgPostMapper.updateByExampleSelective(post, dgPostExample);
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
