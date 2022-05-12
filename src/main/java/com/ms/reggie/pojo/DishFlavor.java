package com.ms.reggie.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 菜品口味关系表(DishFlavor)实体类
 *
 * @author SerMs
 * @since 2022-05-08 16:21:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishFlavor implements Serializable {
    private static final long serialVersionUID = -54590813093165158L;
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 菜品
     */
    @TableField(value = "dish_id")
    private Long dishId;

    /**
     * 口味名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 口味数据list
     */
    @TableField(value = "value")
    private String value;

    /**创建时间*/
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**更新时间*/
    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * 是否删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;
}
