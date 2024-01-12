package com.coco.dragon.resp.post;

import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgPostResp {
    private Long id;

    private Long userId;

    private Long categoryId;

    private String title;

    private String pictures;

    private String remark;

    private Long creatorId;

    private Date createTime;

    private Long updatorId;

    private Date updateTime;

    private Integer flag;

    private Integer status;

    private String content;
}