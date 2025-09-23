package com.negoffer.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.common.convention.result.Results;
import com.negoffer.shortlink.admin.dto.req.UserLoginReqDTO;
import com.negoffer.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.negoffer.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.negoffer.shortlink.admin.dto.resp.UserActualRespDTO;
import com.negoffer.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.negoffer.shortlink.admin.dto.resp.UserRespDTO;
import com.negoffer.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 用户管理控制层
 */
@RestController /*cmd + 左键单击查看源码*/
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查询用户信息
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
        /* results.success(...）构造标准格式返回值 */
    }

    /**
     * 根据用户名查询无脱敏用户信息
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getActualUserByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * Check if the username exists
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUsername(@RequestParam("username") String username) {
        return Results.success(userService.hasUsername(username));
    }

    /**
     * Register user
     *
     * @param requestParam Parameters for the user registration request
     */
    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * Update user
     */
    @PutMapping("/api/short-link/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }

    /**
     * user login
     */
    @PostMapping("/api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login(@RequestBody UserLoginReqDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    /**
     * check if user has logged in
     */
    @GetMapping("/api/short-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("username") String username, @RequestParam("token") String token) {
        return Results.success(userService.checkLogin(username, token));
    }

    /**
     * User log out
     */
    @DeleteMapping("/api/short-link/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("username") String username, @RequestParam("token") String token) {
        userService.logout(username, token);
        return Results.success();
    }
}

/*
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
✅ private final UserService userService;

这是 类的成员变量，用于存储 UserService 实例。
由于它是 final，必须在构造函数里初始化，否则编译器会报错。
✅ public UserController(UserService userService) { this.userService = userService; }

这里 userService 是 构造函数的参数，它是由 Spring 自动传入的 UserService 实例。
this.userService 指的是 类的成员变量。
userService（等号右侧） 指的是 构造函数传入的参数。
this.userService = userService; 就是把传入的 userService 赋值给当前类的 userService。

例子：
假设 Spring 容器管理 UserService 并自动注入 UserController：

@Service
public class UserService {
    public void addUser(String username) {
        System.out.println("User added: " + username);
    }
}

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;  // 赋值
    }

    public void createUser() {
        userService.addUser("Ningyi");  // 调用 userService 方法
    }
}
当 Spring 启动时，它会：
1️⃣ 创建 UserService 实例
UserService userService = new UserService();
2️⃣ 创建 UserController，并把 userService 传进去
UserController userController = new UserController(userService);
3️⃣ this.userService = userService; 让 userController 持有 userService 实例
// 这个赋值发生在构造函数里
this.userService = userService;
这样 userController 里的 userService 变量就指向了 Spring 管理的 UserService 实例，可以正常调用 addUser() 方法。

*/