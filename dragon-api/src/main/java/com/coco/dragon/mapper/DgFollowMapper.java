package com.coco.dragon.mapper;

import com.coco.dragon.domain.DgFollow;
import com.coco.dragon.domain.DgFollowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgFollowMapper {
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