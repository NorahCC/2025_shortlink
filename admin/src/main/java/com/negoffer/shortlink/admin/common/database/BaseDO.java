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

package com.negoffer.shortlink.admin.common.database;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * 数据库持久层对象基础属性
 * 公众号：马丁玩编程，回复：加群，添加马哥微信（备注：link）获取项目资料
 */
@Data
public class BaseDO {

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标识 0：未删除 1：已删除
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delFlag;
}

//这个 `BaseDO` 是一个 **数据库实体的公共父类**，在项目中起到了“统一封装基础字段”的作用。我们来逐行拆解一下它的结构和用途。
//
//        ---
//
//        ## 🧱 文件作用：
//
//封装所有数据库实体（DO）都可能会有的通用字段，例如：
//
//        * 创建时间（`createTime`）
//        * 更新时间（`updateTime`）
//        * 删除标识位（`delFlag`）
//
//当你以后定义具体表的实体类（比如 `UserDO`、`LinkDO` 等）时，只需要继承这个 `BaseDO`，就自动拥有这些字段了。
//
//        ---
//
//        ## 📦 代码拆解与注释
//
//```java
//@Data
//public class BaseDO {
//```
//
//        * `@Data` 是 **
//    Lombok 注解**，自动生成 `getter/setter/toString/hashCode/equals`，减少样板代码。
//
//            ---
//
//            ### ✅ 字段一：创建时间
//
//```java
//    @TableField(fill = FieldFill.INSERT)
//    private Date createTime;
//```
//
//        * 意思是：**插入数据库时自动填充**这个字段。
//            * 适用于记录“这条数据什么时候创建的”。
//
//            ---
//
//            ### ✅ 字段二：更新时间
//
//```java
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private Date updateTime;
//```
//
//        * 意思是：**插入和更新时都自动填充**。
//            * 用来记录最近一次被修改的时间。
//
//            ---
//
//            ### ✅ 字段三：逻辑删除标识位
//
//```java
//    @TableField(fill = FieldFill.INSERT)
//    private Integer delFlag;
//```
//
//        * `0` 表示 **未删除**，`1` 表示 **已删除**。
//            * 这是 MyBatis-Plus 支持的 **逻辑删除机制**，避免真正删除数据库中的数据。
//            * 可以在数据库中保留历史记录，也方便回滚和审计。
//
//            ---
//
//            ## 🧠 这个类的使用方式
//
//    比如你有个 `UserDO`：
//
//            ```java
//    public class UserDO extends BaseDO {
//        private Long id;
//        private String username;
//    }
//```
//
//    那它自动继承了：
//
//            * `createTime`
//            * `updateTime`
//            * `delFlag`
//
//    你可以在数据库表 `user` 中看到这些字段。
//
//            ---
//
//            ## 💡 总结
//
//| 字段           | 用途     | 自动填充时机 |
//            | ------------ | ------ | ------ |
//            | `createTime` | 创建时间   | 插入时    |
//            | `updateTime` | 修改时间   | 插入和更新时 |
//            | `delFlag`    | 逻辑删除标识 | 插入时    |

