package com.coco.dragon.resp.user;

import com.coco.dragon.resp.BaseResp;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.Date;

/**
 * @author liaoshen
 */
@Data
public class DgUserResp extends BaseResp {

    private Integer id;

    private String phone;

    private String nickName;

    private String avatarUrl;

    private String email;

    private String password;

}