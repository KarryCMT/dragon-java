package com.coco.dragon.req.post;

import com.coco.dragon.req.BaseReq;
import com.coco.dragon.req.PageReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgPostQueryReq extends PageReq {

    private String title;

    private Integer status;
}
