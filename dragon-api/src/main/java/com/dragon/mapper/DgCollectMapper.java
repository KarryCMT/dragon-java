package com.dragon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.domain.DgCollect;
import com.dragon.domain.DgCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgCollectMapper extends BaseMapper<DgCollect> {
    long countByExample(DgCollectExample example);

    int deleteByExample(DgCollectExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgCollect record);

    int insertSelective(DgCollect record);

    List<DgCollect> selectByExample(DgCollectExample example);

    DgCollect selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgCollect record, @Param("example") DgCollectExample example);

    int updateByExample(@Param("record") DgCollect record, @Param("example") DgCollectExample example);

    int updateByPrimaryKeySelective(DgCollect record);

    int updateByPrimaryKey(DgCollect record);
}