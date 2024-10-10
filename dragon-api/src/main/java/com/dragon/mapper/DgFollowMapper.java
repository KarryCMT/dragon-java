package com.dragon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.domain.DgFollow;
import com.dragon.domain.DgFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgFollowMapper extends BaseMapper<DgFollow> {
    long countByExample(DgFollowExample example);

    int deleteByExample(DgFollowExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgFollow record);

    int insertSelective(DgFollow record);

    List<DgFollow> selectByExample(DgFollowExample example);

    DgFollow selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgFollow record, @Param("example") DgFollowExample example);

    int updateByExample(@Param("record") DgFollow record, @Param("example") DgFollowExample example);

    int updateByPrimaryKeySelective(DgFollow record);

    int updateByPrimaryKey(DgFollow record);
}