package com.coco.dragon.resp.category;

import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgCategoryResp {
    private Long id;

    private String name;

    private String cover;

    private String remark;

    private Long creatorId;

    private Date createTime;

    private Long updatorId;

    private Date updateTime;

    private Integer status;

    private Integer flag;
}