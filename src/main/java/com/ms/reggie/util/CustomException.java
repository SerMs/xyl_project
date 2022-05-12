package com.ms.reggie.util;

/**
 * <p>
 * 自定义业务异常类
 * </p>
 *
 * @author SerMs
 * @date 2022/5/7 23:49
 */
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
