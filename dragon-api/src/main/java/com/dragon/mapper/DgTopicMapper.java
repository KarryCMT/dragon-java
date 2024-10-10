package com.dragon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.domain.DgTopic;
import com.dragon.domain.DgTopicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgTopicMapper extends BaseMapper<DgTopic> {
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