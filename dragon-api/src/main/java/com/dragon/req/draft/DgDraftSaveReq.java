package com.dragon.req.draft;

import com.dragon.req.BaseReq;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgDraftSaveReq extends BaseReq {

    private Long id;

    @NotNull(  "用户ID不能为空")
    private Long userId;

    @NotNull(  "帖子ID不能为空")
    private Long postId;

    public DgDraftSaveReq() {

    }
}