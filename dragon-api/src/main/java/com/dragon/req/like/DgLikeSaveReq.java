package com.dragon.req.like;

import com.dragon.req.BaseReq;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgLikeSaveReq extends BaseReq {
    private Long id;

    @NotNull( "帖子Id不能为空")
    private Long postId;

    @NotNull( "用户ID不能为空")
    private Long userId;

}