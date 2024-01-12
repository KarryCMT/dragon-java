package com.coco.dragon.req.collect;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgCollectQueryReq extends BaseReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private Integer status;

}
