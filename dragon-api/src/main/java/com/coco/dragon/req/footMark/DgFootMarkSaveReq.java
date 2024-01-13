package com.coco.dragon.req.footMark;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgFootMarkSaveReq extends BaseReq {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "帖子ID不能为空")
    private Long postId;

}