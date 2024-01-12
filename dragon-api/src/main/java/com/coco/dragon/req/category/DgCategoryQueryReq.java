package com.coco.dragon.req.category;

import com.coco.dragon.req.BaseReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgCategoryQueryReq extends BaseReq {

    private String name;

    private Integer status;
}
