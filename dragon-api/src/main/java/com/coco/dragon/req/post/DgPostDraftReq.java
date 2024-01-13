package com.coco.dragon.req.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgPostDraftReq {
    private Long id;

    private Long userId;

    private Long categoryId;

    private String title;

    private String pictures;

    private String content;

    private String remark;

    private Long creatorId;

    private Date createTime;

    private Long updatorId;

    private Date updateTime;

    private Integer status;

    private Integer flag;

}