package com.coco.dragon.resp.comment;

import com.coco.dragon.domain.DgComment;
import com.coco.dragon.resp.BaseResp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author liaoshen
 */
@Data
public class DgCommentResp extends BaseResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long postId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long parentId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long answerId;

    private List<DgComment> children;

    private String content;

    private Integer isAuthor;

}