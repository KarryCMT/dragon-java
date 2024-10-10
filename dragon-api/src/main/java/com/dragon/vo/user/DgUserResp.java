package com.dragon.vo.user;

import com.dragon.vo.BaseResp;
import lombok.Data;

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