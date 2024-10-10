package com.dragon.vo.follow;

import com.dragon.vo.BaseResp;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

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