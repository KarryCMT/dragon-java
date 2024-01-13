package com.coco.dragon.req.user;

import com.coco.dragon.req.BaseReq;
import com.coco.dragon.req.PageReq;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgUserQueryReq extends PageReq {

    private String nickName;

    private String phone;


}
