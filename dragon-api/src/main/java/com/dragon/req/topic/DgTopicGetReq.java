package com.dragon.req.topic;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgTopicGetReq {

    @NotNull(message = "【ID】不能为空")
    private Long id;

}
