package com.coco.dragon.mapper;

import com.coco.dragon.domain.DgComment;
import com.coco.dragon.domain.DgCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DgCommentMapper {
    long countByExample(DgCommentExample example);

    int deleteByExample(DgCommentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DgComment record);

    int insertSelective(DgComment record);

    List<DgComment> selectByExampleWithBLOBs(DgCommentExample example);

    List<DgComment> selectByExample(DgCommentExample example);

    DgComment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DgComment record, @Param("example") DgCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") DgComment record, @Param("example") DgCommentExample example);

    int updateByExample(@Param("record") DgComment record, @Param("example") DgCommentExample example);

    int updateByPrimaryKeySelective(DgComment record);

    int updateByPrimaryKeyWithBLOBs(DgComment record);

    int updateByPrimaryKey(DgComment record);
}