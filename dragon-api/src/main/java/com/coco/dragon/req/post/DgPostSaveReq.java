package com.coco.dragon.req.post;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgPostSaveReq {
    private Long id;

    private Long userId;

    private Long categoryId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "图片不能为空")
    private String pictures;

    @NotBlank(message = "内容不能为空")
    private String content;

    private String remark;

    private Long creatorId;

    private Date createTime;

    private Long updatorId;

    private Date updateTime;

    private Integer status;

    private Integer flag;

}