package com.dragon.req;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class PageReq {

    @NotNull(message = "当前分页不能为空")
    private Integer pageNum;

    @NotNull(message = "分页数量不能为空")
    private Integer pageSize;

    private Integer status;

    private Integer flag;

}
