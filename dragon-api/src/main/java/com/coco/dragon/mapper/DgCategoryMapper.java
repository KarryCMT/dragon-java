package com.coco.dragon.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgCategoryMapper {
    long countByExample(DgCategoryExample example);

    int deleteByExample(DgCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgCategory record);

    int insertSelective(DgCategory record);

    List<DgCategory> selectByExample(DgCategoryExample example);

    DgCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgCategory record, @Param("example") DgCategoryExample example);

    int updateByExample(@Param("record") DgCategory record, @Param("example") DgCategoryExample example);

    int updateByPrimaryKeySelective(DgCategory record);

    int updateByPrimaryKey(DgCategory record);
}