package com.dragon.req.topic;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@Data
public class DgTopicGetReq {

    @NotNull( "【ID】不能为空")
    private Long id;

}
