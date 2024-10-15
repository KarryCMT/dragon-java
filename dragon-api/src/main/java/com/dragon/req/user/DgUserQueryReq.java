package com.dragon.req.user;

import com.dragon.req.PageReq;
import lombok.Data;

/**
 * @author liaoshen
 */
@Data
public class DgUserQueryReq extends PageReq {

    private String nickName;

    private String phone;


}
