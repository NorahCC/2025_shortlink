
package com.negoffer.shortlink.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.negoffer.shortlink.admin.dao.entity.UserDO;

/**
 * 用户持久层
 * 公众号：马丁玩编程，回复：加群，添加马哥微信（备注：link）获取项目资料
 */
public interface UserMapper extends BaseMapper<UserDO> {
}

//
//
//        ## 🧱 一、UserDO 是什么？
//
//全名：**User Data Object**（用户数据对象）
//所在包：`dao.entity`
//
//        ### ✅ 作用：
//
//代表 **数据库中的一行数据**，也就是一个 **用户对象的结构**。
//
//        ### ✅ 映射关系：
//
//        * 通过 `@TableName("t_user")` 映射到数据库表 `t_user`。
//        * 字段如 `id`、`username`、`mail` 会自动和表中列一一对应。
//        * 继承了 `BaseDO`，所以还拥有：`createTime`、`updateTime`、`delFlag`。
//
//        ---
//
//        ### 🧠 举个例子：
//
//你在数据库中有这样一行：
//
//        | id | username | real\_name | phone | mail | deletion\_time |
//        | -- | -------- | ---------- | ----- | ---- | -------------- |
//        | 1  | ningyi   | 宁祎         | 12345 | xxx  | null           |
//
//它就会被 MyBatis-Plus 映射成：
//
//        ```java
//UserDO user = new UserDO();
//user.setId(1L);
//user.setUsername("ningyi");
//user.setRealName("宁祎");
//// ...
//```
//
//        ---
//
//        ## 🧾 二、UserMapper 是什么？
//
//全名：**User 数据访问层 Mapper 接口**
//所在包：`dao.mapper`
//
//        ### ✅ 作用：
//
//定义对 `t_user` 表的各种 **数据库操作方法**（增删改查），比如：
//
//        * `insert(user)`
//        * `selectById(id)`
//        * `updateById(user)`
//        * `deleteById(id)`
//        * `selectList(queryWrapper)` 等
//
//### ✅ 为啥不用自己写方法？
//
//因为它继承了：
//
//        ```java
//BaseMapper<UserDO>
//```
//
//这是 MyBatis-Plus 提供的通用 Mapper，帮你自动实现了 99% 的常用操作，不用再写 XML！
//
//        ---
//
//        ## ✅ 总结对比表：
//
//        | 类名           | 作用                     | 所在位置         | 关键注解/继承                             | 对应层     |
//        | ------------ | ---------------------- | ------------ | ----------------------------------- | ------- |
//        | `UserDO`     | 数据库表结构映射（一个用户对象）       | `dao.entity` | `@TableName("t_user")`, 继承 `BaseDO` | Model 层 |
//        | `UserMapper` | 数据库访问接口（对 t\_user 表操作） | `dao.mapper` | 继承 `BaseMapper<UserDO>`             | DAO 层   |
//
//        ---
//
//        ## 🧩 典型用法示例
//
//在 `UserServiceImpl` 里，你可以直接注入 `UserMapper`，像这样使用：
//
//        ```java
//@Service
//public class UserServiceImpl implements UserService {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public UserDO getUserById(Long id) {
//        return userMapper.selectById(id);
//    }
//}
//```
