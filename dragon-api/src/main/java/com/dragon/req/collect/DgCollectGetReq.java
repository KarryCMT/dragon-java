package com.dragon.req.collect;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgCollectGetReq {

    @NotNull( "用户ID不能为空")
    private Long userId;

    @NotNull( "ID不能为空")
    private Long id;

}