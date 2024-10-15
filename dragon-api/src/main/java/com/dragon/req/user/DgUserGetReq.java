package com.dragon.req.user;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgUserGetReq {

    @NotNull(  "【ID】不能为空")
    private Integer id;

}
