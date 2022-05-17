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
 * 套餐(Setmeal)实体类
 *
 * @author SerMs
 * @since 2022-05-07 23:32:37
 */
@Data
@ApiModel("套餐")
public class Setmeal implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id")
    private Long id;

    /**
     * 菜品分类id
     */
    @ApiModelProperty("分类id")
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 套餐名称
     */
    @ApiModelProperty("套餐名称")
    @TableField(value = "name")
    private String name;

    /**
     * 套餐价格
     */
    @ApiModelProperty("套餐价格")
    @TableField(value = "price")
    private BigDecimal price;

    /**
     * 状态 0:停用 1:启用
     */
    @ApiModelProperty("状态")
    @TableField(value = "status")
    private Integer status;

    /**
     * 编码
     */
    @ApiModelProperty("编码")
    @TableField(value = "code")
    private String code;

    /**
     * 描述信息
     */
    @ApiModelProperty("描述信息")
    @TableField(value = "description")
    private String description;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    @TableField(value = "image")
    private String image;

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
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

}
