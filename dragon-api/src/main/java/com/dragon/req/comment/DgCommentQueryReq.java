package com.dragon.req.comment;

import com.dragon.req.PageReq;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgCommentQueryReq extends PageReq {

    @NotNull("用户ID不能为空")
    private Long userId;

    @NotNull("帖子ID不能为空")
    private Long postId;
}
