package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.coco.dragon.client.UserFeignClient;
import com.coco.dragon.domain.DgCollect;
import com.coco.dragon.domain.DgComment;
import com.coco.dragon.domain.DgCommentExample;
import com.coco.dragon.domain.DgCommentExample;
import com.coco.dragon.mapper.DgCollectMapper;
import com.coco.dragon.mapper.DgCommentMapper;
import com.coco.dragon.req.comment.DgCommentGetReq;
import com.coco.dragon.req.comment.DgCommentQueryReq;
import com.coco.dragon.req.comment.DgCommentSaveReq;
import com.coco.dragon.req.member.MemberReq;
import com.coco.dragon.resp.comment.DgCommentResp;
import com.coco.dragon.resp.user.SsMember;
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
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liaoshen
 */
@Service
@Slf4j
@Scope("prototype")
public class DgCommentService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Resource
    private DgCommentMapper dgCommentMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public PageInfo page(DgCommentQueryReq req) {
        DgCommentExample dgCommentExample = new DgCommentExample();
        DgCommentExample.Criteria criteria = dgCommentExample.createCriteria();
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        //  查询 帖子ID
        if (ObjectUtil.isNotNull(req.getPostId())) {
            criteria.andPostIdEqualTo(req.getPostId());
        }
        //  查询 用户ID
        if (ObjectUtil.isNotNull(req.getUserId())) {
            criteria.andUserIdEqualTo(req.getUserId());
        }
        //  查询状态
        if (ObjectUtil.isNotNull(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        criteria.andFlagEqualTo(1);
        List<DgComment> list = dgCommentMapper.selectByExampleWithBLOBs(dgCommentExample);
        List<DgCommentResp> respList = new ArrayList<>();
        for (DgComment dgComment: list){
            //  用userId去调用 接口 找到对应的用户名称存入 name
            MemberReq memberReq = new MemberReq();
            memberReq.setId(dgComment.getUserId());
            SsMember member = userFeignClient.getMember(memberReq);
            DgCommentResp resp = BeanUtil.copyProperties(dgComment,DgCommentResp.class);
            //
            resp.setName(member.getName());
            resp.setAvatar(member.getAvatar());
            respList.add(resp);
        }
        PageInfo<DgCommentResp> pageInfo = new PageInfo<>(respList);
        pageInfo.setPageNum(req.getPageNum());
        pageInfo.setPageSize(req.getPageSize());
        return pageInfo;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgCommentResp> all(DgCommentGetReq req) {
        DgCommentExample dgCommentExample = new DgCommentExample();
        DgCommentExample.Criteria criteria = dgCommentExample.createCriteria();
        criteria.andFlagEqualTo(1);
        criteria.andPostIdEqualTo(req.getPostId());
        List<DgComment> dgComments = dgCommentMapper.selectByExampleWithBLOBs(dgCommentExample);
        List<DgCommentResp> respList = new ArrayList<>();
        List<DgComment> rootComments = dgComments.stream().filter(comment -> comment.getParentId() == 0).toList();
        for (DgComment rootComment: rootComments){
            respList.add(BeanUtil.copyProperties(rootComment,DgCommentResp.class));
        }
        for (DgCommentResp item:respList){
            //  用userId去调用 接口 找到对应的用户名称存入 name
            MemberReq memberReq = new MemberReq();
            memberReq.setId(item.getUserId());
            SsMember member = userFeignClient.getMember(memberReq);
            item.setAvatar(member.getAvatar());
            item.setName(member.getName());
            item.setChildren(dgComments.stream().filter(comment->comment.getParentId().equals(item.getId())).collect(Collectors.toList()));
        }
        return respList;

    }


    public List<DgCommentResp> ChildAll(DgCommentGetReq req){
        DgCommentExample dgCommentExample = new DgCommentExample();
        DgCommentExample.Criteria criteria = dgCommentExample.createCriteria();
        criteria.andFlagEqualTo(1);
        criteria.andParentIdEqualTo(req.getParentId());
        List<DgComment> dgComments = dgCommentMapper.selectByExampleWithBLOBs(dgCommentExample);
        List<DgCommentResp> respList = new ArrayList<>();
        for (DgComment rootComment: dgComments){
            respList.add(BeanUtil.copyProperties(rootComment,DgCommentResp.class));
        }
        for (DgCommentResp item:respList){
            //  用userId去调用 接口 找到对应的用户名称存入 name
            MemberReq memberReq = new MemberReq();
            MemberReq replyMemberReq = new MemberReq();
            memberReq.setId(item.getUserId());
            replyMemberReq.setId(item.getAnswerId());
            SsMember member = userFeignClient.getMember(memberReq);
            SsMember replyMember = userFeignClient.getMember(replyMemberReq);
            item.setAvatar(member.getAvatar());
            item.setName(member.getName());
            item.setReplyName(replyMember.getName());
            item.setChildren(dgComments.stream().filter(comment->comment.getParentId().equals(item.getId())).collect(Collectors.toList()));
        }
        return respList;

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgCommentResp get(DgCommentGetReq req) {
        DgComment comment = dgCommentMapper.selectByPrimaryKey(req.getId());
        DgCommentResp resp = BeanUtil.copyProperties(comment, DgCommentResp.class);
        return resp;
    }

    /**
     * 新增评论
     *
     * @param req
     * @return
     */
    public int create(DgCommentSaveReq req) {
        DateTime now = DateTime.now();
        DgCommentExample dgCommentExample = new DgCommentExample();
        DgCommentExample.Criteria criteria = dgCommentExample.createCriteria();
        criteria.andPostIdEqualTo(req.getPostId());
        criteria.andUserIdEqualTo(req.getUserId());
        DgComment comment = BeanUtil.copyProperties(req, DgComment.class);
        comment.setId(SnowUtil.getSnowflakeNextId());
        comment.setCreateTime(now);
        comment.setUpdateTime(now);
        comment.setCreatorId(1L);
        comment.setUpdatorId(1L);
        comment.setFlag(1);
        comment.setStatus(1);
        return dgCommentMapper.insert(comment);
    }


    /**
     * 删除评论
     *
     * @param req
     * @return
     */
    public int delete(DgCommentGetReq req) {
        DgCommentExample dgCommentExample = new DgCommentExample();
        DgCommentExample.Criteria criteria = dgCommentExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        criteria.andUserIdEqualTo(req.getUserId());
        DgComment comment = dgCommentMapper.selectByPrimaryKey(req.getId());
        comment.setFlag(0);
        return dgCommentMapper.updateByExampleSelective(comment, dgCommentExample);
    }
}
