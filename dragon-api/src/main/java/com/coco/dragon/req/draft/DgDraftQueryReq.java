package com.coco.dragon.req.draft;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgDraftQueryReq extends BaseReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private Integer status;

}
