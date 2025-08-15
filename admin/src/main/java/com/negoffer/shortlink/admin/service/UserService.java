package com.negoffer.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.admin.dao.entity.UserDO;
import com.negoffer.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.negoffer.shortlink.admin.dto.resp.UserRespDTO;

/**
 * 用户接口层
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * Check if the username exists
     *
     * @param username Username
     * @return Returns True if the username exists, otherwise returns False
     */
    Boolean hasUsername(String username);

    /**
     * Register user
     *
     * @param requestParam Parameters for the user registration request
     */
    void register(UserRegisterReqDTO requestParam);


}

/**
UserService 是一个 Service 接口，继承了 IService<UserDO>：
IService<T> 是 MyBatis-Plus 提供的 通用 Service 接口，提供crud。
UserDO 是实体类，对应 t_user 数据表。

 在 Service 层，通常需要一个实现类 UserServiceImpl，继承 ServiceImpl<T> 并实现 UserService

 逻辑梳理： 添加service层，在serviceImpl里实现业务逻辑，然后controller只需要调用userservice，这样，controller不再直接依赖mapper,而是通过service层调用业务逻辑
 */
