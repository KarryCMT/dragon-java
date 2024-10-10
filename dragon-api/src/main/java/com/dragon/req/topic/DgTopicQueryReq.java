package com.dragon.req.topic;

import com.dragon.req.PageReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgTopicQueryReq extends PageReq {

    private String name;

    private Integer status;
}
