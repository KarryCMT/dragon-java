package com.coco.dragon.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class BaseReq {
    @NotBlank(message = "当前分页不能为空")
    private Integer pageNum;

    @NotBlank(message = "分页数量不能为空")
    private Integer pageSize;

    private Integer id;
    /**
     * 创建人
     */
    private Long creatorId;

    /**
     * 更新人
     */
    private Long updatorId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    /**
     * 标志：0-已删除、1-未删除
     */
    private Integer flag;

    private static final long serialVersionUID = 1L;

}
