package com.coco.dragon.req.post;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgPostDraftReq extends BaseReq {
    private Long id;

    private Long userId;

    private Long topicId;

    private String title;

    private String pictures;

    private String content;

    private String remark;

}