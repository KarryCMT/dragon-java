package com.dragon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.domain.DgLike;
import com.dragon.domain.DgLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgLikeMapper extends BaseMapper<DgLike> {
    long countByExample(DgLikeExample example);

    int deleteByExample(DgLikeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgLike record);

    int insertSelective(DgLike record);

    List<DgLike> selectByExample(DgLikeExample example);

    DgLike selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgLike record, @Param("example") DgLikeExample example);

    int updateByExample(@Param("record") DgLike record, @Param("example") DgLikeExample example);

    int updateByPrimaryKeySelective(DgLike record);

    int updateByPrimaryKey(DgLike record);
}