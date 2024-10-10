package com.dragon.vo.comment;

import com.dragon.domain.DgComment;
import com.dragon.vo.BaseResp;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

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

    private String name;

    private String avatar;

    private Integer isAuthor;

    private String replyName;

}