package com.dragon.req.follow;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgFollowCancelReq {

    @NotNull(  "用户ID不能为空")
    private Long userId;

    @NotNull(  "关注用户ID不能为空")
    private Long followedId;

}