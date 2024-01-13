package com.coco.dragon.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
