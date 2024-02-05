package com.coco.dragon.req.topic;

import com.coco.dragon.req.PageReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgTopicQueryReq extends PageReq {

    private String name;

    private Integer status;
}
