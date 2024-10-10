package com.dragon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dragon.domain.DgDraft;
import com.dragon.domain.DgDraftExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgDraftMapper extends BaseMapper<DgDraft> {
    long countByExample(DgDraftExample example);

    int deleteByExample(DgDraftExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgDraft record);

    int insertSelective(DgDraft record);

    List<DgDraft> selectByExample(DgDraftExample example);

    DgDraft selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgDraft record, @Param("example") DgDraftExample example);

    int updateByExample(@Param("record") DgDraft record, @Param("example") DgDraftExample example);

    int updateByPrimaryKeySelective(DgDraft record);

    int updateByPrimaryKey(DgDraft record);
}