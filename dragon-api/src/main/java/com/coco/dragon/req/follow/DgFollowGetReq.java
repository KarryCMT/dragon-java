package com.coco.dragon.req.follow;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgFollowGetReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "ID不能为空")
    private Long id;

}