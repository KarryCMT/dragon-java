package com.coco.dragon.req.follow;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgFollowCancelReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "关注用户ID不能为空")
    private Long followedId;

}