package com.coco.dragon.resp.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgCategoryResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private String cover;

    private String remark;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long creatorId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long updatorId;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    private Integer status;

    private Integer flag;
}