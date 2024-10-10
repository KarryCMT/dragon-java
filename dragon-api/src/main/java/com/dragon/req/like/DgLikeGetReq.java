package com.dragon.req.like;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgLikeGetReq {

    private Long postId;

    private Long id;

}
