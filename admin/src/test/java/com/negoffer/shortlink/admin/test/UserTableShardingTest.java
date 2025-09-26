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

package com.negoffer.shortlink.admin.test;

public class UserTableShardingTest {

    public static final String SQL = "CREATE TABLE IF NOT EXISTS link.`t_user_%d` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `username` varchar(256) DEFAULT NULL COMMENT '用户名',\n" +
            "  `password` varchar(512) DEFAULT NULL COMMENT '密码',\n" +
            "  `real_name` varchar(256) DEFAULT NULL COMMENT '真实姓名',\n" +
            "  `phone` varchar(128) DEFAULT NULL COMMENT '手机号',\n" +
            "  `mail` varchar(512) DEFAULT NULL COMMENT '邮箱',\n" +
            "  `deletion_time` bigint(20) DEFAULT NULL COMMENT '注销时间戳',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT '创建时间',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT '修改时间',\n" +
            "  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识 0：未删除 1：已删除',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `idx_unique_username` (`username`) USING BTREE\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1715030926162935810 DEFAULT CHARSET=utf8mb4;";

    public static final String SQL2 =  "CREATE TABLE IF NOT EXISTS link.`t_link_%d` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `domain` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '域名',\n" +
            "  `short_uri` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '短链接',\n" +
            "  `full_short_url` varchar(100) DEFAULT NULL COMMENT '完整短链接',\n" +
            "  `origin_url` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '原始链接',\n" +
            "  `click_num` int DEFAULT '0' COMMENT '点击量',\n" +
            "  `gid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'default_value' COMMENT '分组标识',\n" +
            "  `favicon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '网站图标',\n" +
            "  `enable_status` tinyint DEFAULT NULL COMMENT '启用标识 0: 已启用 1: 未启用',\n" +
            "  `created_type` tinyint DEFAULT NULL COMMENT '创建类型 0:接口创建 1:控制台创建',\n" +
            "  `valid_date_type` tinyint DEFAULT NULL COMMENT '有效期类型 0:永久有效 1:自定义',\n" +
            "  `valid_date` datetime DEFAULT NULL COMMENT '有效期',\n" +
            "  `describe` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '描述',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT 'creation time',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT 'modification ts',\n" +
            "  `del_flag` tinyint(1) DEFAULT NULL COMMENT 'deleted: 1; otherwise: 0',\n" +
            "  PRIMARY KEY (`id`),\n" +
            "  UNIQUE KEY `idx_unique_full_short_url` (`short_uri`,`full_short_url`) USING BTREE\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1970252494688370691 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";

    public static final String SQL3 =
            "CREATE TABLE IF NOT EXISTS shortlink.`t_group_%d` (\n" +
            "  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',\n" +
            "  `gid` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分组标识',\n" +
            "  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '分组名称',\n" +
            "  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '创建分组用户名',\n" +
            "  `sort_order` int DEFAULT NULL COMMENT '分组排序',\n" +
            "  `create_time` datetime DEFAULT NULL COMMENT 'creation time',\n" +
            "  `update_time` datetime DEFAULT NULL COMMENT 'modification ts',\n" +
            "  `del_flag` tinyint(1) DEFAULT NULL COMMENT 'deleted: 1; otherwise: 0',\n" +
            "  PRIMARY KEY (`id`)\n" +
            ") ENGINE=InnoDB AUTO_INCREMENT=1968062165852573699 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";



    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.printf((SQL3) + "%n", i);
        }
    }
}