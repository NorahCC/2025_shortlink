package com.negoffer.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.negoffer.shortlink.admin.common.convention.exception.ClientException;
import com.negoffer.shortlink.admin.common.convention.exception.ServiceException;
import com.negoffer.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.negoffer.shortlink.admin.dao.entity.UserDO;
import com.negoffer.shortlink.admin.dao.mapper.UserMapper;
import com.negoffer.shortlink.admin.dto.req.UserLoginReqDTO;
import com.negoffer.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.negoffer.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.negoffer.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.negoffer.shortlink.admin.dto.resp.UserRespDTO;
import com.negoffer.shortlink.admin.service.GroupService;
import com.negoffer.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.negoffer.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;
import static com.negoffer.shortlink.admin.common.enums.UserErrorCodeEnum.*;

/**
 * 实现层
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final GroupService groupService;

    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ServiceException(UserErrorCodeEnum.USER_NULL);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }

    @Override
    public Boolean hasUsername(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (!hasUsername(requestParam.getUsername())) {
            throw new ClientException(USER_NAME_EXIST);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try {
            if (lock.tryLock()) {
                try {
                    int inserted = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                    if (inserted < 1) {
                        throw new ClientException(USER_SAVE_ERROR);
                    }
                } catch (DuplicateKeyException ex) {
                    throw new ClientException(USER_EXIST);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                groupService.saveGroup(requestParam.getUsername(), "默认分组");
                return;
            }
            throw new ClientException(USER_NAME_EXIST);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void update(UserUpdateReqDTO requestParam) {
        // TODO Check if the current user is the same as the logged-in user
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class), updateWrapper);
    }


    @Override
    public UserLoginRespDTO login (UserLoginReqDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
            .eq(UserDO::getUsername, requestParam.getUsername())
            .eq(UserDO::getPassword, requestParam.getPassword())
            .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException("User does not exist");
    }
        Boolean hasLogin = stringRedisTemplate.hasKey("login_" + requestParam.getUsername());
        if (hasLogin != null && hasLogin){
            throw new ClientException("User has already logged in");
        }
        /**
         * Hash
         * Key：login_用户名
         * Value：
         *  Key：token标识
         *  Val：JSON 字符串（用户信息）
         */
        String uuid = UUID.randomUUID().toString();
//        stringRedisTemplate.opsForValue().set(uuid, JSON.toJSONString(userDO), 30L, TimeUnit.MINUTES);
//
//        Map<String, Object> userInfoMap = new HashMap<>();
//        userInfoMap.put("token", JSON.toJSONString(userDO));

        stringRedisTemplate.opsForHash().put("login_" + requestParam.getUsername(), uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + requestParam.getUsername(), 30L, TimeUnit.DAYS);
        return new UserLoginRespDTO(uuid);
}

    @Override
    public Boolean checkLogin(String username, String token) {
        return stringRedisTemplate.opsForHash().get("login_" + username, token) != null;
    }

    @Override
    public void logout(String username, String token) {
        if (checkLogin(username, token)) {
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException("User token does not exist or the user is not logged in");
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