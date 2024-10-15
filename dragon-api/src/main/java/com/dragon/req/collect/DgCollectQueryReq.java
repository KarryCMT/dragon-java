package com.dragon.req.collect;

import com.dragon.req.PageReq;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgCollectQueryReq extends PageReq {

    @NotNull("用户ID不能为空")
    private Long userId;

    private Integer status;

}
