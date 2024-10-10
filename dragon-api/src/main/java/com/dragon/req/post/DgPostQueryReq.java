package com.dragon.req.post;

import com.dragon.req.PageReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgPostQueryReq extends PageReq {

    private String title;

    private Integer status;
}
