package com.coco.dragon.req.collect;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgCollectSaveReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "帖子ID不能为空")
    private Long postId;

}