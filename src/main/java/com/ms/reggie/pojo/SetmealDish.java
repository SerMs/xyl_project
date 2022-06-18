package com.ms.reggie.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 套餐菜品关系(SetmealDish)实体类
 *
 * @author SerMs
 * @since 2022-05-08 23:14:59
 */
@Data
@ApiModel("订单表(Orders)实体类")
public class SetmealDish implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id")
    private Long id;

    /**
     * 套餐id
     */
    @ApiModelProperty("套餐id")
    @TableField(value = "setmeal_id")
    private Long setmealId;

    /**
     * 菜品id
     */
    @ApiModelProperty("菜品id")
    @TableField(value = "dish_id")
    private Long dishId;

    /**
     * 菜品名称 （冗余字段）
     */
    @ApiModelProperty(" 菜品名称 （冗余字段）")
    @TableField(value = "name")
    private String name;

    /**
     * 菜品原价（冗余字段）
     */
    @ApiModelProperty("菜品原价（冗余字段）")
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 份数
     */
    @ApiModelProperty("份数")
    @TableField(value = "copies")
    private Integer copies;

    /**
     * 排序
     */
    @ApiModelProperty(" 排序")
    @TableField(value = "sort")
    private Integer sort;

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
