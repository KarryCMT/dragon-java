package com.coco.dragon.req.post;

import com.coco.dragon.req.BaseReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgPostQueryReq extends BaseReq {

    private String title;

    private Integer status;
}
