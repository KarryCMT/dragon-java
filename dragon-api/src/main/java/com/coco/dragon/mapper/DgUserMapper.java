package com.coco.dragon.mapper;

import com.coco.dragon.domain.DgUser;
import com.coco.dragon.domain.DgUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgUserMapper {
    long countByExample(DgUserExample example);

    int deleteByExample(DgUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DgUser record);

    int insertSelective(DgUser record);

    List<DgUser> selectByExample(DgUserExample example);

    DgUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DgUser record, @Param("example") DgUserExample example);

    int updateByExample(@Param("record") DgUser record, @Param("example") DgUserExample example);

    int updateByPrimaryKeySelective(DgUser record);

    int updateByPrimaryKey(DgUser record);
}