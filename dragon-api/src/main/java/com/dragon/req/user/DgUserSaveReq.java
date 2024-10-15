package com.dragon.req.user;

import com.dragon.req.BaseReq;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author liaoshen
 */
@Data
public class DgUserSaveReq extends BaseReq {

    private Integer id;

    @NotBlank(message = "【手机号】不能为空")
    private String phone;

    @NotBlank(message = "【昵称】不能为空")
    private String nickName;

    @NotBlank(message = "【头像】不能为空")
    private String avatarUrl;

    @NotBlank(message = "【邮箱】不能为空")
    private String email;

    @NotBlank(message = "【密码】不能为空")
    private String password;

}
