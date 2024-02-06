package com.coco.dragon.req.like;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgLikeSaveReq extends BaseReq {
    private Long id;

    @NotNull(message = "帖子Id不能为空")
    private Long postId;

    @NotNull(message = "用户ID不能为空")
    private Long userId;

}