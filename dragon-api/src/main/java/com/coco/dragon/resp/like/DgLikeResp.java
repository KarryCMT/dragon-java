package com.coco.dragon.resp.like;

import com.coco.dragon.resp.BaseResp;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgLikeResp extends BaseResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private String cover;

    private String remark;

}