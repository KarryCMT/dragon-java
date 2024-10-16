package com.dragon.req.post;

import com.dragon.req.BaseReq;
import lombok.Data;

import javax.validation.constraints.NotBlank;

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