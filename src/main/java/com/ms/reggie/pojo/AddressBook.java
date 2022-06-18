package com.ms.reggie.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地址管理(AddressBook)实体类
 *
 * @author SerMs
 * @since 2022-05-11 21:17:40
 */
@Data
@ApiModel("地址实体类")
public class AddressBook implements Serializable {
    private static final long serialVersionUID = 792812272937596360L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 收货人
     */
    @ApiModelProperty("收货人")
    @TableField(value = "consignee")
    private String consignee;

    /**
     * 性别 0 女 1 男
     */
    @ApiModelProperty("性别")
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @TableField(value = "phone")
    private String phone;

    /**
     * 省级区划编号
     */
    @ApiModelProperty("省级区划编号")
    @TableField(value = "province_code")
    private String provinceCode;

    /**
     * 省级名称
     */
    @ApiModelProperty("省级名称")
    @TableField(value = "province_name")
    private String provinceName;

    /**
     * 市级区划编号
     */
    @ApiModelProperty("市级区划编号")
    @TableField(value = "city_code")
    private String cityCode;

    /**
     * 市级名称
     */
    @ApiModelProperty("市级名称")
    @TableField(value = "city_name")
    private String cityName;

    /**
     * 区级区划编号
     */
    @ApiModelProperty("区级区划编号")
    @TableField(value = "district_code")
    private String districtCode;

    /**
     * 区级名称
     */
    @ApiModelProperty("区级名称")
    @TableField(value = "district_name")
    private String districtName;

    /**
     * 详细地址
     */
    @ApiModelProperty("详细地址")
    @TableField(value = "detail")
    private String detail;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    @TableField(value = "label")
    private String label;

    /**
     * 默认 0 否 1是
     */
    @ApiModelProperty("是否默认地址")
    @TableField(value = "is_default")
    private Integer isDefault;

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

    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
