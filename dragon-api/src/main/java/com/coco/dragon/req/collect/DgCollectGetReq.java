package com.coco.dragon.req.collect;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgCollectGetReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "ID不能为空")
    private Long id;

}