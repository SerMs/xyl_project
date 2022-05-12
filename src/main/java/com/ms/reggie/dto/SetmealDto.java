package com.ms.reggie.dto;

import com.ms.reggie.pojo.Setmeal;
import com.ms.reggie.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
