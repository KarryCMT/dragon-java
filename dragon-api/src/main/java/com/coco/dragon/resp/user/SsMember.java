package com.coco.dragon.resp.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * ss_member
 * @author 
 */
@Data
@EqualsAndHashCode
public class SsMember implements Serializable {
    /**
     * 成员的ID
     */
    private Long id;

    /**
     * 成员的部门ID
     */
    private String deptId;

    /**
     * 成员的岗位ID
     */
    private String jobId;

    /**
     * 成员姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String identityCardNo;

    /**
     * 工号
     */
    private String code;

    /**
     * 成员的账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * snt前端加密的密码
     */
    private String sntPassword;

    /**
     * 手势密码
     */
    private String gesturePassword;

    /**
     * 成员的昵称
     */
    private String nickName;

    /**
     * 搜索码
     */
    private String searchCode;

    /**
     * 用户类型：00系统用户 2用户创建
     */
    private Integer type;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 用户性别：0-未知，1-男，2-女
     */
    private Integer gender;

    /**
     * 头像(地址)
     */
    private String avatar;

    /**
     * 状态：0-停用，1-启用
     */
    private Integer status;

    /**
     * 用户在钉钉中的id
     */
    private String dingdingUserId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 锁定时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date lockTime;

    /**
     * 锁定到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date lockExpireTime;

    /**
     * 失败次数
     */
    private Integer failCount;

    /**
     * 字段创建者ID
     */
    private Long creatorId;

    /**
     * 字段创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    /**
     * 字段更新者ID
     */
    private Long updatorId;

    /**
     * 字段更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss" ,timezone = "GMT+8")
    private Date updateTime;

    /**
     * 标志：0-已删除，1-未删除
     */
    private Integer flag;

    /**
     * ad域账户名称
     */
    private String adUserName;

    /**
     * ad域登录名称
     */
    private String adLoginName;

    private static final long serialVersionUID = 1L;
}