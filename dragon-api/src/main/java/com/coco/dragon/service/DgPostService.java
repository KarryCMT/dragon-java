package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.coco.dragon.client.OssFeignClient;
import com.coco.dragon.client.UserFeignClient;
import com.coco.dragon.domain.DgCollectExample;
import com.coco.dragon.domain.DgCommentExample;
import com.coco.dragon.domain.DgPost;
import com.coco.dragon.domain.DgPostExample;
import com.coco.dragon.mapper.DgCollectMapper;
import com.coco.dragon.mapper.DgCommentMapper;
import com.coco.dragon.mapper.DgPostMapper;
import com.coco.dragon.req.collect.DgCollectSaveReq;
import com.coco.dragon.req.draft.DgDraftSaveReq;
import com.coco.dragon.req.follow.DgFollowSaveReq;
import com.coco.dragon.req.like.DgLikeGetReq;
import com.coco.dragon.req.like.DgLikeSaveReq;
import com.coco.dragon.req.member.MemberReq;
import com.coco.dragon.req.oss.OssReq;
import com.coco.dragon.req.post.DgPostDraftReq;
import com.coco.dragon.req.post.DgPostGetReq;
import com.coco.dragon.req.post.DgPostQueryReq;
import com.coco.dragon.req.post.DgPostSaveReq;
import com.coco.dragon.resp.oss.SdFile;
import com.coco.dragon.resp.post.DgPostResp;
import com.coco.dragon.resp.user.SsMember;
import com.coco.dragon.util.ApiClient;
import com.coco.rabbit.common.exception.RabbitException;
import com.coco.rabbit.common.util.SnowUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private OssFeignClient ossFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Resource
    private DgPostMapper dgPostMapper;

    @Resource
    private DgCommentMapper dgCommentMapper;

    @Resource
    private DgCollectMapper dgCollectMapper;

    @Resource
    private DgDraftService dgDraftService;

    @Resource
    private DgLikeService dgLikeService;

    @Resource
    private DgCollectService dgCollectService;

    @Resource
    private DgFollowService dgFollowService;

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

        List<DgPost> list = dgPostMapper.selectByExampleWithBLOBs(dgPostExample);
        PageInfo<DgPostResp> pageInfo = new PageInfo<>();
        List<DgPostResp> postRespArrayList = new ArrayList<>();
        for (DgPost dgPost : list) {
            OssReq ossReq = new OssReq();
            MemberReq memberReq = new MemberReq();
            memberReq.setId(dgPost.getUserId());
            ossReq.setPicturesIds(dgPost.getPictures());
            //用userId去调用 接口 找到对应的用户名称存入 name
            SsMember member = userFeignClient.getMember(memberReq);
            List<SdFile> listByIds = ossFeignClient.getFileListByIds(ossReq);
            if (member != null) {
                DgPostResp dgPostResp= new DgPostResp();
                DgLikeGetReq likeGetReq = new DgLikeGetReq();
                likeGetReq.setPostId(dgPost.getId());
                long likeCount = dgLikeService.all(likeGetReq);
                DgLikeSaveReq dgLikeSaveReq = new DgLikeSaveReq();
                dgLikeSaveReq.setPostId(dgPost.getId());
                dgLikeSaveReq.setUserId(dgPost.getUserId());
                boolean isLike = dgLikeService.isLike(dgLikeSaveReq);
                dgPostResp.setLikeCount(likeCount);
                dgPostResp.setIsLike(isLike);
                dgPostResp.setId(dgPost.getId());
                dgPostResp.setContent(dgPost.getContent());
                dgPostResp.setTitle(dgPost.getTitle());
                dgPostResp.setPicturesList(listByIds);
                dgPostResp.setUserId(dgPost.getUserId());
                dgPostResp.setAvatar(member.getAvatar());
                dgPostResp.setName(member.getName());
                dgPostResp.setTopicId(dgPost.getTopicId());
                dgPostResp.setStatus(dgPost.getStatus());
                postRespArrayList.add(dgPostResp);
            }
        }
        pageInfo.setList(postRespArrayList);
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
        OssReq ossReq = new OssReq();
        MemberReq memberReq = new MemberReq();
        memberReq.setId(post.getUserId());
        ossReq.setPicturesIds(post.getPictures());
        //用userId去调用 接口 找到对应的用户名称存入 name
        SsMember member = userFeignClient.getMember(memberReq);
        List<SdFile> listByIds = ossFeignClient.getFileListByIds(ossReq);
        DgPostResp dgPostResp = BeanUtil.copyProperties(post, DgPostResp.class);

//        点赞
        DgLikeGetReq likeGetReq = new DgLikeGetReq();
        likeGetReq.setPostId(post.getId());
        long likeCount = dgLikeService.all(likeGetReq);
        DgLikeSaveReq dgLikeSaveReq = new DgLikeSaveReq();
        dgLikeSaveReq.setPostId(post.getId());
        dgLikeSaveReq.setUserId(req.getUserId());
        boolean isLike = dgLikeService.isLike(dgLikeSaveReq);
//        评论
        DgCommentExample dgCommentExample = new DgCommentExample();
        DgCommentExample.Criteria criteria = dgCommentExample.createCriteria();
        criteria.andFlagEqualTo(1);
        criteria.andPostIdEqualTo(req.getId());
        long commentCount = dgCommentMapper.countByExample(dgCommentExample);
//        收藏
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteriaCollect = dgCollectExample.createCriteria();
        criteriaCollect.andFlagEqualTo(1);
        criteriaCollect.andPostIdEqualTo(req.getId());
        long collectCount = dgCollectMapper.countByExample(dgCollectExample);
        DgCollectSaveReq dgCollectSaveReq = new DgCollectSaveReq();
        dgCollectSaveReq.setPostId(req.getId());
        dgCollectSaveReq.setUserId(req.getUserId());
        boolean isCollect = dgCollectService.isCollect(dgCollectSaveReq);
//        关注
        DgFollowSaveReq dgFollowSaveReq = new DgFollowSaveReq();
        dgFollowSaveReq.setFollowedId(post.getUserId());
        dgFollowSaveReq.setUserId(req.getUserId());
        boolean isFollow = dgFollowService.isFollow(dgFollowSaveReq);

        dgPostResp.setName(member.getName());
        dgPostResp.setAvatar(member.getAvatar());
        dgPostResp.setPicturesList(listByIds);
        dgPostResp.setLikeCount(likeCount);
        dgPostResp.setIsLike(isLike);
        dgPostResp.setIsCollect(isCollect);
        dgPostResp.setCommentCount(commentCount);
        dgPostResp.setCollectCount(collectCount);
        dgPostResp.setIsFollow(isFollow);
        return dgPostResp;
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
