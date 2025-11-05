package com.negoffer.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.negoffer.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity for basic short link access statistics
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_link_access_stats")
public class LinkAccessStatsDO extends BaseDO {

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
     * Date
     */
    private Date date;

    /**
     * Page views
     */
    private Integer pv;

    /**
     * Unique visitors
     */
    private Integer uv;

    /**
     * Unique IP count
     */
    private Integer uip;

    /**
     * Hour of the day
     */
    private Integer hour;

    /**
     * Day of the week
     */
    private Integer weekday;
}
