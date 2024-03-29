package com.coco.dragon.req.draft;

import com.coco.dragon.req.BaseReq;
import com.coco.dragon.req.PageReq;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgDraftQueryReq extends PageReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private Integer status;

}
