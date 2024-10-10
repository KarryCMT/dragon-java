package com.dragon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.domain.DgFootMark;
import com.dragon.domain.DgFootMarkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgFootMarkMapper extends BaseMapper<DgFootMark> {
    long countByExample(DgFootMarkExample example);

    int deleteByExample(DgFootMarkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgFootMark record);

    int insertSelective(DgFootMark record);

    List<DgFootMark> selectByExample(DgFootMarkExample example);

    DgFootMark selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgFootMark record, @Param("example") DgFootMarkExample example);

    int updateByExample(@Param("record") DgFootMark record, @Param("example") DgFootMarkExample example);

    int updateByPrimaryKeySelective(DgFootMark record);

    int updateByPrimaryKey(DgFootMark record);
}