package com.coco.dragon.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.cloud.commons.lang.StringUtils;
import com.coco.dragon.domain.DgUser;
import com.coco.dragon.domain.DgUserExample;
import com.coco.dragon.mapper.DgUserMapper;
import com.coco.dragon.req.user.DgUserGetReq;
import com.coco.dragon.req.user.DgUserQueryReq;
import com.coco.dragon.req.user.DgUserSaveReq;
import com.coco.dragon.resp.DgUserResp;
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
public class DgUserService {

    @Resource
    private DgUserMapper dgUserMapper;

    /**
     * 获取用户分页
     *
     * @param
     * @return
     */
    public PageInfo page(DgUserQueryReq req) {
        DgUserExample dgUserExample = new DgUserExample();
        DgUserExample.Criteria criteria = dgUserExample.createCriteria();
        PageHelper.startPage(req.getPageNum(), req.getPageSize());
        //  模糊查询 昵称
        if (StringUtils.isNotBlank(req.getNickName())) {
            criteria.andNickNameLike('%' + req.getNickName() + '%');
        }
        //  模糊查询手机号
        if (StringUtils.isNotBlank(req.getPhone())) {
            criteria.andPhoneLike('%' + req.getPhone() + '%');
        }
        //  查询状态
        if (ObjectUtil.isNotNull(req.getStatus())) {
            criteria.andStatusEqualTo(req.getStatus());
        }
        criteria.andFlagEqualTo(1);
        List<DgUser> list = dgUserMapper.selectByExample(dgUserExample);
        PageInfo<DgUser> pageInfo = new PageInfo<>(list);
        pageInfo.setPageNum(req.getPageNum());
        pageInfo.setPageSize(req.getPageSize());
        return pageInfo;
    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgUserResp get(DgUserGetReq req) {
        DgUser user = dgUserMapper.selectByPrimaryKey(req.getId());
        DgUserResp resp = BeanUtil.copyProperties(user, DgUserResp.class);
        return resp;
    }

    /**
     * 新增
     *
     * @param req
     * @return
     */
    public int create(DgUserSaveReq req) {
        DateTime now = DateTime.now();
        DgUser user = BeanUtil.copyProperties(req, DgUser.class);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setCreatorId(1L);
        user.setUpdatorId(1L);
        return dgUserMapper.insert(user);
    }

    /**
     * 删除
     *
     * @param req
     * @return
     */
    public int delete(DgUserGetReq req) {
        DgUserExample dgUserExample = new DgUserExample();
        DgUserExample.Criteria criteria = dgUserExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        DgUser user = dgUserMapper.selectByPrimaryKey(req.getId());
        user.setFlag(0);
        return dgUserMapper.updateByExample(user, dgUserExample);
    }
}
