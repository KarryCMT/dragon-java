package com.dragon.req.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgUserGetReq {

    @NotNull(message = "【ID】不能为空")
    private Integer id;

}
