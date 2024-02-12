package com.coco.dragon.resp.post;

import com.coco.dragon.resp.BaseResp;
import com.coco.dragon.resp.oss.SdFile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liaoshen
 */
@Data
public class DgPostResp extends BaseResp {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long topicId;

    private String title;

    private String avatar;

    private String name;

    private List<SdFile> picturesList;

    private String remark;

    private String content;

    /**
     * 点赞数
     */
    private Long likeCount = 0L;

    /**
     * 是不是当前人点的赞  false-不是  true-是
     */
    private Boolean isLike = false;
}