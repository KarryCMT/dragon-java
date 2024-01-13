package com.coco.dragon.req.category;

import com.coco.dragon.req.BaseReq;
import com.coco.dragon.req.PageReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgCategoryQueryReq extends PageReq {

    private String name;

    private Integer status;
}
