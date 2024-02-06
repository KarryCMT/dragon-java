package com.coco.dragon.req.post;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgPostSaveReq extends BaseReq {
    private Long id;

    private Long userId;

    private Long topicId;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "图片不能为空")
    private String pictures;

    @NotBlank(message = "内容不能为空")
    private String content;

    private String remark;

}