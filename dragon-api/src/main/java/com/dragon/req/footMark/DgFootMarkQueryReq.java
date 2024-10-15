package com.dragon.req.footMark;

import com.dragon.req.PageReq;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgFootMarkQueryReq extends PageReq {

    @NotNull( "用户ID不能为空")
    private Long userId;

    @NotNull( "开始日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @NotNull( "结束日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
