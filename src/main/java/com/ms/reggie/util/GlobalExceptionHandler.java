package com.ms.reggie.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * <p>
 * 全局异常捕获
 * </p>
 *
 * @author SerMs
 * @date 2022/5/7 15:59
 */
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 异常处理方法
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException exception) {
        log.info(exception.getMessage());
        if (exception.getMessage().contains("Duplicate entry")) {
            String[] s = exception.getMessage().split(" ");
            String msg = s[2] + "已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException exception) {
        log.info(exception.getMessage());
        return R.error(exception.getMessage());
    }

    /**
     * 图片路径异常 61d20592-b37f-4d72-a864-07ad5bb8f3bb.jpg (系统找不到指定的文件。)
     * @param exception
     * @return
     */
//    @ExceptionHandler(FileNotFoundException.class)
//    public R<String> exceptionHandler(FileNotFoundException exception) {
//        log.info(exception.getMessage());
//        return R.error(exception.getMessage());
//    }
//
//    @ExceptionHandler(FileSizeLimitExceededException.class)
//    public R<String> exceptionHandler(FileSizeLimitExceededException exception) {
//        log.info(exception.getMessage());
//        return R.error("文件大于2MB");
//    }
}
