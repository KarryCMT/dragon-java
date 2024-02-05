package com.coco.dragon.mapper;

import com.coco.dragon.domain.DgTopic;
import com.coco.dragon.domain.DgTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgTopicMapper {
    long countByExample(DgTopicExample example);

    int deleteByExample(DgTopicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgTopic record);

    int insertSelective(DgTopic record);

    List<DgTopic> selectByExample(DgTopicExample example);

    DgTopic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgTopic record, @Param("example") DgTopicExample example);

    int updateByExample(@Param("record") DgTopic record, @Param("example") DgTopicExample example);

    int updateByPrimaryKeySelective(DgTopic record);

    int updateByPrimaryKey(DgTopic record);
}