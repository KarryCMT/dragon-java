package com.coco.dragon.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BaseResp {
    /**
     * 创建人
     */
    private Long creatorId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
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
