package com.coco.dragon.req.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgCommentGetReq {

    @NotNull(message = "帖子ID不能为空")
    private Long postId;

    private Long id;

    private Long userId;

    private Long ParentId;

}