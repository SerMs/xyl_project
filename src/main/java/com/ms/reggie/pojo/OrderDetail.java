package com.ms.reggie.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单明细表(OrderDetail)实体类
 *
 * @author SerMs
 * @since 2022-05-12 09:05:50
 */
@Data
@ApiModel("订单明细表(OrderDetail)实体类")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -26388713536403861L;
    /**
     * 主键
     */
    @TableId(value = "id")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 名字
     */
    @TableField(value = "name")
    @ApiModelProperty("名字")
    private String name;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    @TableField(value = "image")
    private String image;

    /**
     * 订单id
     */
    @ApiModelProperty("订单id")
    @TableField(value = "order_id")
    private Long orderId;

    /**
     * 菜品id
     */
    @ApiModelProperty("菜品id")
    @TableField(value = "dish_id")
    private Long dishId;

    /**
     * 套餐id
     */
    @ApiModelProperty("套餐id")
    @TableField(value = "setmeal_id")
    private Long setmealId;

    /**
     * 口味
     */
    @ApiModelProperty("口味")
    @TableField(value = "dish_flavor")
    private String dishFlavor;

    /**
     * 数量
     */
    @ApiModelProperty("数量")
    @TableField(value = "number")
    private Integer number;

    /**
     * 金额
     */
    @ApiModelProperty("金额")
    @TableField(value = "amount")
    private BigDecimal amount;
}
