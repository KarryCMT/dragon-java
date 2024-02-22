package com.coco.dragon.resp.follow;

import com.coco.dragon.resp.BaseResp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgFollowResp extends BaseResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long followedId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    private String name;

    private String avatar;

    private String desc;

}