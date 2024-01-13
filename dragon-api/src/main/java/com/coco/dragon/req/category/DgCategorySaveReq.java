package com.coco.dragon.req.category;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgCategorySaveReq extends BaseReq {
    private Long id;

    @NotBlank(message = "分类名称不能为空")
    private String name;

    @NotBlank(message = "分类ICON不能为空")
    private String cover;

    private String remark;

}