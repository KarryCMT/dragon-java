package com.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.client.OssFeignClient;
import com.dragon.client.UserFeignClient;
import com.dragon.domain.DgCollectExample;
import com.dragon.domain.DgCommentExample;
import com.dragon.domain.DgPost;
import com.dragon.domain.DgPostExample;
import com.dragon.mapper.DgCollectMapper;
import com.dragon.mapper.DgCommentMapper;
import com.dragon.mapper.DgPostMapper;
import com.dragon.req.collect.DgCollectSaveReq;
import com.dragon.req.draft.DgDraftSaveReq;
import com.dragon.req.follow.DgFollowSaveReq;
import com.dragon.req.like.DgLikeGetReq;
import com.dragon.req.like.DgLikeSaveReq;
import com.dragon.req.member.MemberReq;
import com.dragon.req.oss.OssReq;
import com.dragon.req.post.DgPostDraftReq;
import com.dragon.req.post.DgPostGetReq;
import com.dragon.req.post.DgPostQueryReq;
import com.dragon.req.post.DgPostSaveReq;
import com.dragon.vo.oss.SdFile;
import com.dragon.vo.post.DgPostResp;
import com.dragon.vo.user.SsMember;
import com.monkey.common.bean.PageInfo;
import com.monkey.common.exception.BaseRuntimeException;
import com.monkey.common.utils.SnowflakeIdWorkerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liaoshen
 */
public interface DgPostService extends IService<DgPost> {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgPostQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgPost> all();

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgPostResp get(DgPostGetReq req);

    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int create(DgPostSaveReq req);


    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int draft(DgPostDraftReq req);


    /**
     * 修改
     *
     * @param req
     * @return
     */
    public int update(DgPostSaveReq req);

    /**
     * 删除
     *
     * @param req
     * @return
     */
    public int delete(DgPostGetReq req);
}
