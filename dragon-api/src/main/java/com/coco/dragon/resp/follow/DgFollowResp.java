package com.coco.dragon.resp.follow;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgFollowResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long followedId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    private Integer status;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long creatorId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long updatorId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    private Integer flag;

}