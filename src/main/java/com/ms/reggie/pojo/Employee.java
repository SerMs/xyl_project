package com.ms.reggie.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 员工信息(Employee)实体类
 *
 * @author SerMs
 * @since 2022-05-07 10:57:42
 */
@Data
@ApiModel("员工信息(Employee)实体类")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @TableField(value = "password")
    private String password;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @TableField(value = "phone")
    private String phone;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    @TableField(value = "sex")
    private String sex;

    /**
     * 身份证号
     */
    @ApiModelProperty("身份证号")
    @TableField(value = "id_number")
    private String idNumber;

    /**
     * 状态 0:禁用，1:正常
     */
    @ApiModelProperty("状态 0:禁用，1:正常")
    @TableField(value = "status")
    private Integer status;

    /**创建时间*/
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**更新时间*/
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
}