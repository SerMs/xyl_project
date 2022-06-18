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
 * 菜品管理(Dish)实体类
 *
 * @author SerMs
 * @since 2022-05-07 23:32:34
 */
@Data
@ApiModel("菜品管理(Dish)实体类")
public class Dish implements Serializable {
    private static final long serialVersionUID = 807226314892916147L;
    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id")
    private Long id;

    /**
     * 菜品名称
     */
    @ApiModelProperty("菜品名称")
    @TableField(value = "name")
    private String name;

    /**
     * 菜品分类id
     */
    @ApiModelProperty("菜品分类id")
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 菜品价格
     */
    @ApiModelProperty("菜品价格")
    @TableField(value = "price")
    private Double price;

    /**
     * 商品码
     */
    @ApiModelProperty("商品码")
    @TableField(value = "code")
    private String code;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    @TableField(value = "image")
    private String image;

    /**
     * 描述信息
     */
    @ApiModelProperty("描述信息")
    @TableField(value = "description")
    private String description;

    /**
     * 0 停售 1 起售
     */
    @ApiModelProperty("0 停售 1 起售")
    @TableField(value = "status")
    private Integer status;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
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
