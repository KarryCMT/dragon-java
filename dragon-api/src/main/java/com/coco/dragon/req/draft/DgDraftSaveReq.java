package com.coco.dragon.req.draft;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgDraftSaveReq {

    private Long id;

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "帖子ID不能为空")
    private Long postId;

    private Integer status;

    private Long creatorId;

    private Date createTime;

    private Long updatorId;

    private Date updateTime;

    private Integer flag;

}