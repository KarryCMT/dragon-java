package com.dragon.req.draft;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgDraftGetReq {

    @NotNull( "用户ID不能为空")
    private Long userId;

    @NotNull(  "帖子ID不能为空")
    private Long postId;

    @NotNull( "ID不能为空")
    private Long id;

}