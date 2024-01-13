package com.coco.dragon.resp.category;

import com.coco.dragon.resp.BaseResp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgCategoryResp extends BaseResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private String cover;

    private String remark;

}