package com.coco.dragon.req.follow;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liaoshen
 */
@Data
public class DgFollowSaveReq extends BaseReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "被关注人ID不能为空")
    private Long followedId;

}