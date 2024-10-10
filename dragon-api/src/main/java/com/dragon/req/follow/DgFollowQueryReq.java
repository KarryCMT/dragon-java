package com.dragon.req.follow;

import com.dragon.req.PageReq;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgFollowQueryReq extends PageReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    private Integer status;

}
