package com.coco.dragon.req.follow;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgFollowSaveReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "被关注人ID不能为空")
    private Long followedId;

}