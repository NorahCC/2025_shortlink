/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.negoffer.shortlink.admin.common.web;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.negoffer.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.negoffer.shortlink.admin.common.convention.exception.AbstractException;
import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.common.convention.result.Results;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;
import java.util.Optional;

/**
 * 全局异常处理器
 */
@Component("globalExceptionHandlerByAdmin")
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截参数验证异常
     */
    @SneakyThrows
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result validExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        FieldError firstFieldError = CollectionUtil.getFirst(bindingResult.getFieldErrors());
        String exceptionStr = Optional.ofNullable(firstFieldError)
                .map(FieldError::getDefaultMessage)
                .orElse(StrUtil.EMPTY);
        log.error("[{}] {} [ex] {}", request.getMethod(), getUrl(request), exceptionStr);
        return Results.failure(BaseErrorCode.CLIENT_ERROR.code(), exceptionStr);
    }

    /**
     * 拦截应用内抛出的异常
     */
    @ExceptionHandler(value = {AbstractException.class})
    public Result abstractException(HttpServletRequest request, AbstractException ex) {
        if (ex.getCause() != null) {
            log.error("[{}] {} [ex] {}", request.getMethod(), request.getRequestURL().toString(), ex.toString(), ex.getCause());
            return Results.failure(ex);
        }
        log.error("[{}] {} [ex] {}", request.getMethod(), request.getRequestURL().toString(), ex.toString());
        return Results.failure(ex);
    }

    /**
     * 拦截未捕获异常
     */
    @ExceptionHandler(value = Throwable.class)
    public Result defaultErrorHandler(HttpServletRequest request, Throwable throwable) {
        log.error("[{}] {} ", request.getMethod(), getUrl(request), throwable);
        // 注意，此处是为了聚合模式添加的代码，正常不需要该判断
        if (Objects.equals(throwable.getClass().getSuperclass().getSimpleName(), AbstractException.class.getSimpleName())) {
            String errorCode = ReflectUtil.getFieldValue(throwable, "errorCode").toString();
            String errorMessage = ReflectUtil.getFieldValue(throwable, "errorMessage").toString();
            return Results.failure(errorCode, errorMessage);
        }
        return Results.failure();
    }

    private String getUrl(HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getQueryString())) {
            return request.getRequestURL().toString();
        }
        return request.getRequestURL().toString() + "?" + request.getQueryString();
    }
}

//
//这是一个 **全局异常处理器**，用于捕获 Controller 层抛出的异常，并统一封装成 `Result` 返回。
//
//属于 Spring Boot 中 `@ControllerAdvice` 的典型用法。
//
//        ---
//
//        ## 🔧 核心功能：
//
//        ### 1. **拦截参数校验失败**
//
//        ```java
//@ExceptionHandler(MethodArgumentNotValidException.class)
//```
//
//        * 处理 `@Valid`、`@Validated` 注解导致的校验失败
//* 提取第一个字段错误信息并返回
//* 用到：`CollectionUtil.getFirst(...)`
//
//        ---
//
//        ### 2. **拦截自定义业务异常**
//
//        ```java
//@ExceptionHandler(AbstractException.class)
//```
//
//        * 捕获你项目中自定义的业务异常类（如 `ClientException`、`ServerException`）
//        * 返回统一结构的错误码 + 提示语
//
//---
//
//        ### 3. **兜底异常捕获**
//
//        ```java
//@ExceptionHandler(Throwable.class)
//```
//
//        * 捕获所有未处理的异常
//* 特殊逻辑：如果是 `AbstractException` 的子类但没有被正确识别，用反射提取 `errorCode` 和 `errorMessage`
//
//        ---
//
//        ## 🧩 用到的其他类/工具：
//
//        | 类                    | 来源         | 作用                     |
//        | -------------------- | ---------- | ---------------------- |
//        | `Result`             | 自定义返回结构    | 封装统一返回格式               |
//        | `Results`            | 构造返回对象的工具类 | `Results.failure(...)` |
//        | `BaseErrorCode`      | 枚举类        | 提供标准错误码                |
//        | `CollectionUtil`     | Hutool 工具类 | 获取列表第一个元素              |
//        | `StrUtil`            | Hutool 工具类 | 处理空字符串                 |
//        | `ReflectUtil`        | Hutool 工具类 | 反射获取字段值                |
//        | `HttpServletRequest` | Servlet    | 获取请求元数据                |
//        | `Slf4j`              | 日志注解       | 打印错误日志                 |
//
//        ---
//
//        ## 🔗 与其他模块的关系：
//
//        | 关系对象                     | 作用                           |
//        | ------------------------ | ---------------------------- |
//        | Controller 层             | 所有接口抛出的异常会进入这里               |
//        | DTO 参数                   | 如果加了 `@Valid` 注解，校验失败也会被这里处理 |
//        | `AbstractException` 及其子类 | 与 `exception` 模块配合使用         |
//        | `Results` 工具类            | 返回统一响应结构                     |
//        | `BaseErrorCode` 枚举       | 提供默认错误码                      |
//
//        ---
//
//        ## ✅ 小结一句话：
//
//        > `GlobalExceptionHandler` 是你项目中负责“集中管理所有异常”的核心类，**用来替代 try-catch**，保证接口返回结构一致，还能自动记录日志和提取错误信息。
//
//        ---

