package com.ms.reggie.util;

/**
 * <p>
 * 基于ThreadLocal封装工具类,用户保存和获取当前登录用户id
 * </p>
 *
 * @author SerMs
 * @date 2022/5/7 21:36
 */
//@Component

import lombok.extern.slf4j.Slf4j;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
@Slf4j
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 获取值
     *
     * @return
     */
    public static Long getCurrentId() {
        log.info("当前登录的用户id为:{}", threadLocal.get());
        return threadLocal.get();
    }

    /**
     * 设置值
     *
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }
}
