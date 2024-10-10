package com.dragon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.domain.DgPost;
import com.dragon.domain.DgPostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgPostMapper extends BaseMapper<DgPost> {
    long countByExample(DgPostExample example);

    int deleteByExample(DgPostExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgPost record);

    int insertSelective(DgPost record);

    List<DgPost> selectByExampleWithBLOBs(DgPostExample example);

    List<DgPost> selectByExample(DgPostExample example);

    DgPost selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgPost record, @Param("example") DgPostExample example);

    int updateByExampleWithBLOBs(@Param("record") DgPost record, @Param("example") DgPostExample example);

    int updateByExample(@Param("record") DgPost record, @Param("example") DgPostExample example);

    int updateByPrimaryKeySelective(DgPost record);

    int updateByPrimaryKeyWithBLOBs(DgPost record);

    int updateByPrimaryKey(DgPost record);
}