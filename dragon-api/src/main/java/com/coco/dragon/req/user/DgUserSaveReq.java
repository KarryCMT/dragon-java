package com.coco.dragon.req.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgUserSaveReq {

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

    private Long creatorId;

    private Date createTime;

    private Long updatorId;

    private Date updateTime;

    private Integer status;

    private Integer flag;

}
