package com.coco.dragon.req.category;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgCategoryGetReq {

    @NotNull(message = "【ID】不能为空")
    private Long id;

}
