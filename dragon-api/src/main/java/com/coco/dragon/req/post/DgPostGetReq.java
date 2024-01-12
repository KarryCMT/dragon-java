package com.coco.dragon.req.post;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgPostGetReq {

    @NotNull(message = "【ID】不能为空")
    private Long id;

}
