package com.coco.dragon.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class BaseResp {
    /**
     * 创建人
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long creatorId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long updatorId;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    /**
     * 删除标识
     */
    private Integer flag;
}
