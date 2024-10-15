package com.dragon.req.post;

import com.dragon.req.BaseReq;
import lombok.Data;

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