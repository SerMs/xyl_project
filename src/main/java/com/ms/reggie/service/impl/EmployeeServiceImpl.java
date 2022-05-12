package com.ms.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ms.reggie.mapper.EmployeeMapper;
import com.ms.reggie.pojo.Employee;
import com.ms.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * 员工信息(Employee)表服务实现类
 *
 * @author SerMs
 * @since 2022-05-07 11:01:28
 */
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}

