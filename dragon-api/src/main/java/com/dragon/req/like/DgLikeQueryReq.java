package com.dragon.req.like;

import com.dragon.req.PageReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgLikeQueryReq extends PageReq {

    private String name;

    private Integer status;
}
