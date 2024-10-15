package com.dragon.req.comment;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgCommentGetReq {

    @NotNull( "帖子ID不能为空")
    private Long postId;

    private Long id;

    private Long userId;

    private Long ParentId;

}