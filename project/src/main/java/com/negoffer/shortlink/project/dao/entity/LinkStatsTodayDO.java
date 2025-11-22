package com.negoffer.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.negoffer.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Short Link Today's Statistics Entity
 */
@TableName("t_link_stats_today")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkStatsTodayDO extends BaseDO {

    /**
     * ID
     */
    private Long id;

    /**
     * Group Identifier
     */
    private String gid;

    /**
     * Full Short Link
     */
    private String fullShortUrl;

    /**
     * Date
     */
    private Date date;

    /**
     * Today's PV (Page Views)
     */
    private Integer todayPv;

    /**
     * Today's UV (Unique Visitors)
     */
    private Integer todayUv;

    /**
     * Today's IP Count (Unique IPs)
     */
    private Integer todayUip;
}
