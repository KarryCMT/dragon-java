package com.dragon.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.monkey.common.exception.BaseRuntimeException;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.domain.DgCollect;
import com.dragon.domain.DgCollectExample;
import com.dragon.mapper.DgCollectMapper;
import com.dragon.req.collect.DgCollectGetReq;
import com.dragon.req.collect.DgCollectQueryReq;
import com.dragon.req.collect.DgCollectSaveReq;
import com.dragon.service.DgCollectService;
import com.dragon.vo.collect.DgCollectVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liaoshen
 */
@Service
public class DgCollectServiceImpl extends ServiceImpl<DgCollectMapper, DgCollect> implements DgCollectService {

    /**
     * 获取分页
     *
     * @param req
     * @return
     */
    @Override
    public Page page(DgCollectQueryReq req) {
        Page<DgCollect> page = new Page<>(req.getPageNum(),req.getPageSize());

        return baseMapper.selectPage(page,getWrapper(req));
    }

    private LambdaQueryWrapper<DgCollect> getWrapper(DgCollectQueryReq req){
        LambdaQueryWrapper<DgCollect> wrapper = Wrappers.lambdaQuery();
        //  查询 用户ID
        wrapper.eq(req.getUserId() != null, DgCollect::getUserId, req.getUserId());
        //  查询状态
        wrapper.eq(req.getStatus() != null, DgCollect::getStatus, req.getStatus());
        return wrapper;
    }

    /**
     * 获取所有
     *
     * @return
     */
    public List<DgCollect> all() {
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteria = dgCollectExample.createCriteria();
        criteria.andFlagEqualTo(1);
        return baseMapper.selectByExample(dgCollectExample);

    }

    /**
     * 查询单条
     *
     * @param req
     * @return
     */
    public DgCollectVo get(DgCollectGetReq req) {
        DgCollect collect = baseMapper.selectByPrimaryKey(req.getId());
        DgCollectVo resp = BeanUtil.copyProperties(collect, DgCollectVo.class);
        return resp;
    }

    /**
     * 新增收藏
     *
     * @param req
     * @return
     */
    public int create(DgCollectSaveReq req) {
        DateTime now = DateTime.now();
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteria = dgCollectExample.createCriteria();
        criteria.andPostIdEqualTo(req.getPostId());
        criteria.andUserIdEqualTo(req.getUserId());
        List<DgCollect> list= baseMapper.selectByExample(dgCollectExample);
        if (list.size() == 1){
            throw new BaseRuntimeException(null,"该帖子已收藏");
        }
        DgCollect collect = BeanUtil.copyProperties(req, DgCollect.class);
        collect.setCreateTime(now);
        collect.setUpdateTime(now);
        collect.setCreatorId(1L);
        collect.setUpdatorId(1L);
        collect.setFlag(1);
        collect.setStatus(1);
        return baseMapper.insert(collect);
    }

    /**
     * 查询是否收藏
     *
     * @param req
     * @return
     */
    public boolean isCollect(DgCollectSaveReq req) {
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteria = dgCollectExample.createCriteria();
        criteria.andPostIdEqualTo(req.getPostId());
        criteria.andUserIdEqualTo(req.getUserId());
        criteria.andFlagEqualTo(1);
        int len = baseMapper.selectByExample(dgCollectExample).size();
        boolean bool = len == 1;
        return bool;
    }


    /**
     * 取消收藏
     *
     * @param req
     * @return
     */
    public int cancel(DgCollectGetReq req) {
        DgCollectExample dgCollectExample = new DgCollectExample();
        DgCollectExample.Criteria criteria = dgCollectExample.createCriteria();
        criteria.andIdEqualTo(req.getId());
        criteria.andUserIdEqualTo(req.getUserId());
        DgCollect collect = baseMapper.selectByPrimaryKey(req.getId());
        collect.setFlag(0);
        return baseMapper.updateByExampleSelective(collect, dgCollectExample);
    }
}
