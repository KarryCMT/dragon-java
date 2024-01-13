package com.coco.dragon.resp.footMark;

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
public class DgFootMarkResp extends BaseResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long postId;

    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

}