package com.ms.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ms.reggie.pojo.Employee;
import com.ms.reggie.service.EmployeeService;
import com.ms.reggie.util.BaseContext;
import com.ms.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 员工信息(Employee)表控制层
 *
 * @author SerMs
 * @since 2022-05-07 11:01:28
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    /**
     * 服务对象
     */
    @Resource
    private EmployeeService employeeService;


    /**
     * 员工登录
     *
     * @param employee 接收实体类
     * @param request  返回结果
     * @return 返回结果
     */
    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request) {
        //将页面提交的密码进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //根据用户名查询数据库信息
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //如果没有查询到则返回登录失败结果
        if (emp == null) {
            return R.error("登录失败");
        }

        //密码比对
        if (!emp.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        //查看员工状态,如果以为禁用状态,则返回员工已经禁用结果
        if (emp.getStatus() == 0) {
            return R.error("账号以冻结");
        }

        //登录成功,将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }


    /**
     * 员工退出
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        //清理Session中保存的当前登录员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    /**
     * 新增员工
     *
     * @param employee 员工实体
     * @return 返回
     */
    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {
        log.info("新增员工信息:{}", employee.toString());


        //设置初始密码123456,需要进行MD5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        //获得当前登录用户的id
        Long empId = (Long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }


    /**
     * 员工信息分页查询
     *
     * @param page     页数
     * @param pageSize 条数
     * @param name     查询的姓名
     * @return 返回
     */
    @GetMapping("/page")
    @CrossOrigin
    public R<Page> page(@Param("page") int page, int pageSize, String name) {
        log.info("page = {},pageSize = {} ,name = {}", page, pageSize, name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        //添加一个过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);

        //排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);

        //执行查询
        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }


    /**
     * 根据id修改员工信息
     *
     * @param employee
     * @return
     */
    @PutMapping
    @CrossOrigin
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        log.info(employee.toString());
        //线程id
        long id = Thread.currentThread().getId();
        log.info("线程id为:{}", id);
        employeeService.updateById(employee);

        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @CrossOrigin
    public R<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询员工信息");
        //获取当前登录用户id
        Long userId = BaseContext.getCurrentId();
        log.info("=====:{}", userId);
        //先判断是否是管理员登录
        if (userId == 1 || id.equals(userId)) {
            Employee employee = employeeService.getById(id);
            if (employee != null) {
                return R.success(employee);
            }
            return R.error("出错了~");
        }
        return R.error("您没有权限,请联系管理员");
    }


    /**
     * 删除员工
     *
     * @param employee
     * @return
     */
    @DeleteMapping
    @CrossOrigin
    public R<String> deleteByDefault(@RequestBody Employee employee) {
        log.info("根据id删除员工信息:{}", employee.toString());
        employeeService.removeById(employee.getId());
        return R.success("删除成功");
    }
}

