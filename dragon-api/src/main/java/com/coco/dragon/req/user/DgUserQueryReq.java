package com.coco.dragon.req.user;

import com.coco.dragon.req.BaseReq;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgUserQueryReq extends BaseReq {

    private Integer id;

    private String nickName;

    private String phone;

    private Integer status;

}
