package com.dragon.req.topic;

import com.dragon.req.BaseReq;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgTopicSaveReq extends BaseReq {
    private Long id;

    @NotBlank(message = "话题名称不能为空")
    private String name;

    @NotBlank(message = "话题ICON不能为空")
    private String cover;

    private String remark;

}