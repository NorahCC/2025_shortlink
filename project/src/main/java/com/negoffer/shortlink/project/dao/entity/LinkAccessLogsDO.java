package com.negoffer.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.negoffer.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity for access log monitoring
 */
@Data
@TableName("t_link_access_logs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkAccessLogsDO extends BaseDO {

    /**
     * ID
     */
    private Long id;

    /**
     * Full short link
     */
    private String fullShortUrl;

    /**
     * Group identifier
     */
    private String gid;

    /**
     * User information
     */
    private String user;

    /**
     * Browser
     */
    private String browser;

    /**
     * Operating system
     */
    private String os;

    /**
     * IP address
     */
    private String ip;
}
