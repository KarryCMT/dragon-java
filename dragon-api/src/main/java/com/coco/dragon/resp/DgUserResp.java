package com.coco.dragon.resp;

import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgUserResp {
    private Integer id;

    private String phone;

    private String nickName;

    private String avatarUrl;

    private String email;

    private String password;

    private Long creatorId;

    private Date createTime;

    private Long updatorId;

    private Date updateTime;

    private Integer status;

    private Integer flag;
}