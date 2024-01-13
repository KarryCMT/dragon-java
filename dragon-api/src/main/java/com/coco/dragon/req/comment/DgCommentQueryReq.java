package com.coco.dragon.req.comment;

import com.coco.dragon.req.BaseReq;
import com.coco.dragon.req.PageReq;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgCommentQueryReq extends PageReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "帖子ID不能为空")
    private Long postId;
}
