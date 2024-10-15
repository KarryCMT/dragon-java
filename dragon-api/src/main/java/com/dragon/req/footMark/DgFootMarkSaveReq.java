package com.dragon.req.footMark;

import com.dragon.req.BaseReq;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgFootMarkSaveReq extends BaseReq {

    @NotNull(  "用户ID不能为空")
    private Long userId;

    @NotNull(  "帖子ID不能为空")
    private Long postId;

}