package com.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dragon.client.UserFeignClient;
import com.dragon.domain.DgComment;
import com.dragon.domain.DgCommentExample;
import com.dragon.mapper.DgCommentMapper;
import com.dragon.req.comment.DgCommentGetReq;
import com.dragon.req.comment.DgCommentQueryReq;
import com.dragon.req.comment.DgCommentSaveReq;
import com.dragon.req.member.MemberReq;
import com.dragon.vo.comment.DgCommentResp;
import com.dragon.vo.user.SsMember;
import com.monkey.common.bean.PageInfo;
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
public interface DgCommentService extends IService<DgComment> {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    public Page page(DgCommentQueryReq req);

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgCommentResp> all(DgCommentGetReq req);


    public List<DgCommentResp> ChildAll(DgCommentGetReq req);

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgCommentResp get(DgCommentGetReq req);

    /**
     * 新增评论
     *
     * @param req
     * @return
     */
    public int create(DgCommentSaveReq req);


    /**
     * 删除评论
     *
     * @param req
     * @return
     */
    public int delete(DgCommentGetReq req);
}
