package com.dragon.vo.topic;

import com.dragon.vo.BaseResp;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgTopicResp extends BaseResp {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    private String name;

    private String cover;

    private String remark;

}