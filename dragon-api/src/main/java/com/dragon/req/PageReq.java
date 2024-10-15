package com.dragon.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

/**
 * @author liaoshen
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageReq {

    @NotNull("当前分页不能为空")
    private Integer pageNum;

    @NotNull("分页数量不能为空")
    private Integer pageSize;

    private Integer status;

    private Integer flag;


}
