package com.negoffer.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.negoffer.shortlink.admin.dao.entity.UserDO;
import com.negoffer.shortlink.admin.dao.mapper.UserMapper;
import com.negoffer.shortlink.admin.dto.resp.UserRespDTO;
import com.negoffer.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 实现层
 * 公众号：马丁玩编程，回复：加群，添加马哥微信（备注：link）获取项目资料
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {


    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        return userDO == null;
    }

}

/**
 *  UserService 是一个 Service 接口，继承了 IService<UserDO>：
 *  IService<T> 是 MyBatis-Plus 提供的 通用 Service 接口，提供crud。
 *  UserDO 是实体类，对应 t_user 数据表。
 *
 *  在 Service 层，通常需要一个实现类 UserServiceImpl，继承 ServiceImpl<T> 并实现 UserService
 *ServiceImpl 是 MyBatis-Plus 默认提供的 Service 实现类，它已经实现了 IService<T> 里的 CRUD 方法。
UserMapper 是 MyBatis-plus 生成的 Mapper（DAO 层），用于访问数据库。
UserDO 是 数据库表 t_user 对应的实体类。
 UserServiceImpl 继承 ServiceImpl<UserMapper, UserDO>，直接获得 CRUD 方法的实现，只需编写业务逻辑即可。


 逻辑梳理： 添加service层，在serviceImpl里实现业务逻辑 -> 调用mapper和do，返回respdto，然后controller只需要调用userservice，这样，controller不再直接依赖mapper,而是通过service层调用业务逻辑
 */