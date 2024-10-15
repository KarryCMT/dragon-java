package com.dragon.req.post;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgPostGetReq {

    @NotNull("【ID】不能为空")
    private Long id;

    private Long userId;

}
