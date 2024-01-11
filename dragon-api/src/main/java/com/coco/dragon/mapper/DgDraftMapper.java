package com.coco.dragon.mapper;

import com.coco.dragon.domain.DgDraft;
import com.coco.dragon.domain.DgDraftExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgDraftMapper {
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