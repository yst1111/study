package com.yst.app.controller;

import cn.hutool.core.lang.Console;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


/**
 * 统一异常处理器
 * @creator: ly-yangst
 * @date: 2022/7/5
 */
@RestControllerAdvice //Controller的增强器
public class ExceptionAdvice {

    @SneakyThrows
    @ExceptionHandler(Exception.class)
    public Map doException(Exception ex){

        Console.log("异常 {}",ex);
        Map<String, Object> ExcMessagemap = new HashMap();
        ExcMessagemap.put("Exception.Cause",ex.getCause());
        ExcMessagemap.put("Exception.Message",ex.getMessage());
        return ExcMessagemap;

    }

}
