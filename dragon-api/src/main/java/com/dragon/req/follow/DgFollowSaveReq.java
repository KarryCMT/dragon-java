package com.dragon.req.follow;

import com.dragon.req.BaseReq;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgFollowSaveReq extends BaseReq {

    @NotNull(  "用户ID不能为空")
    private Long userId;

    @NotNull(  "被关注人ID不能为空")
    private Long followedId;

}