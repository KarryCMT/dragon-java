package com.dragon.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.client.UserFeignClient;
import com.dragon.domain.DgCollect;
import com.dragon.domain.DgComment;
import com.dragon.domain.DgCommentExample;
import com.dragon.mapper.DgCollectMapper;
import com.dragon.mapper.DgCommentMapper;
import com.dragon.req.collect.DgCollectQueryReq;
import com.dragon.req.comment.DgCommentGetReq;
import com.dragon.req.comment.DgCommentQueryReq;
import com.dragon.req.comment.DgCommentSaveReq;
import com.dragon.req.member.MemberReq;
import com.dragon.service.DgCollectService;
import com.dragon.service.DgCommentService;
import com.dragon.vo.comment.DgCommentResp;
import com.dragon.vo.user.SsMember;
import com.monkey.common.bean.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author liaoshen
 */
@Service
@Slf4j
@Scope("prototype")
public class DgCommentServiceImpl extends ServiceImpl<DgCommentMapper, DgComment> implements DgCommentService {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private DgCommentMapper dgCommentMapper;

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    @Override
    public Page page(DgCommentQueryReq req) {
        /**
         * 1、分页查询评论表内容
         *
         * 2、根据评论表内容获取到用户ids 远程调用用户服务查询评论用户信息
         *
         * 3、组合返回的对象
         */

        Page<DgComment> page = new Page<>(req.getPageNum(),req.getPageSize());
        baseMapper.selectPage(page,getWrapper(req));
        List<Long> userIds = page.getRecords().stream().map(DgComment::getUserId).collect(Collectors.toList());
        Map<Long, SsMember> userByIds = getUserByIds(userIds);
        List<DgCommentResp> data = page.getRecords().stream().map(v -> {
            SsMember member = userByIds.get(v.getUserId());

            DgCommentResp resp = BeanUtil.copyProperties(v, DgCommentResp.class);
            resp.setName(member.getName());
            resp.setAvatar(member.getAvatar());
            resp.setChildren(page.getRecords().stream().filter(comment -> comment.getParentId().equals(v.getId())).collect(Collectors.toList()));
            return resp;
        }).collect(Collectors.toList());
        Page newPage = BeanUtil.copyProperties(page, Page.class);
        newPage.setRecords(data);
        return newPage;

    }

    private LambdaQueryWrapper<DgComment> getWrapper(DgCommentQueryReq req){
        LambdaQueryWrapper<DgComment> wrapper = Wrappers.lambdaQuery();
        //  查询 帖子ID
        wrapper.eq(req.getPostId() != null, DgComment::getPostId, req.getPostId());
        //  查询 用户ID
        wrapper.eq(req.getUserId() != null, DgComment::getUserId, req.getUserId());
        //  查询状态
        wrapper.eq(req.getStatus() != null, DgComment::getStatus, req.getStatus());

        //  查询未删除的评论
        wrapper.eq(DgComment::getFlag,1);
        return wrapper;
    }

    /**
     * 根据用户ids查询用户列表
     * @param userIds
     * @return
     */
    public Map<Long,SsMember> getUserByIds(List<Long> userIds){
        return userFeignClient.getMemberByIds(userIds);
    }

    /**
     * 获取所有
     *
     * @return
     */
    @Override
    public List<DgCommentResp> all(DgCommentGetReq req) {
        DgCommentExample dgCommentExample = new DgCommentExample();
        DgCommentExample.Criteria criteria = dgCommentExample.createCriteria();
        criteria.andFlagEqualTo(1);
        criteria.andPostIdEqualTo(req.getPostId());
        List<DgComment> dgComments = dgCommentMapper.selectByExampleWithBLOBs(dgCommentExample);
        List<DgCommentResp> respList = new ArrayList<>();
        List<DgComment> rootComments = dgComments.stream().filter(comment -> comment.getParentId() == 0).collect(Collectors.toList());
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
    @Override
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
    @Override
    public int create(DgCommentSaveReq req) {
        DateTime now = DateTime.now();
        DgCommentExample dgCommentExample = new DgCommentExample();
        DgCommentExample.Criteria criteria = dgCommentExample.createCriteria();
        criteria.andPostIdEqualTo(req.getPostId());
        criteria.andUserIdEqualTo(req.getUserId());
        DgComment comment = BeanUtil.copyProperties(req, DgComment.class);
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
    @Override
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
