package com.dragon.vo.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * ss_member
 * @author 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RbMember implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 成员的ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 成员的部门ID
     */
    private Long deptId;

    /**
     * 成员的岗位ID
     */
    private Long jobId;

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
    private Byte gender;

    /**
     * 头像(地址)
     */
    private String avatar;

    /**
     * 状态：0-停用，1-启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 锁定时间
     */
    private Date lockTime;

    /**
     * 锁定到期时间
     */
    private Date lockExpireTime;

    /**
     * 失败次数
     */
    private Integer failCount;

    /**
     * 字段创建者ID
     */
    @TableField(value = "creator_id",fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 字段创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 字段更新者ID
     */
    @TableField(value = "updator_id",fill = FieldFill.UPDATE)
    private Long updatorId;

    /**
     * 字段更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 标志：0-已删除，1-未删除
     */
    @TableLogic
    @TableField(value = "flag")
    private Integer flag;
}