package com.dragon.vo.oss;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * sd_file
 * @author 
 */
@Data
public class SdFile implements Serializable {

    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 物理文件名
     */
    private String name;

    /**
     * 类型：1-身份证、2-驾照、3-头像、4-发票、5-标书。。。
     */
    private Integer type;

    /**
     * 类型的名字
     */
    private String typeName;

    /**
     * 版本
     */
    private String version;

    /**
     * 应用:1-rabbit、2-panda、3-dog、4-swan
     */
    private Integer appId;

    /**
     * 模块ID，如是投标文件，这里就是投标的ID，如是合同，这里就是合同的ID
     */
    private Long modeId;

    /**
     * 存放路径
     */
    private String savePath;

    /**
     * 大小（KB）
     */
    private Long fileSize;

    /**
     * 大小(kb)废弃
     */
    private Long size;

    /**
     * 状态：0-未审核；1-已审核
     */
    private Integer status;

    /**
     * 文件标识：0-默认临时文件，1-正式文件
     */
    private Integer tempFile;

    /**
     * 备注
     */
    private String remark;

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
     * 标志：0-已删除、1-未删除
     */
    private Integer flag;

    private static final long serialVersionUID = 1L;
}