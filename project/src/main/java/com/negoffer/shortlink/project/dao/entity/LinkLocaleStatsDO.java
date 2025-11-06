package com.negoffer.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.negoffer.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity for regional access statistics
 */
@Data
@TableName("t_link_locale_stats")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkLocaleStatsDO extends BaseDO {

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
     * Visit count
     */
    private Integer cnt;

    /**
     * Province name
     */
    private String province;

    /**
     * City name
     */
    private String city;

    /**
     * City code
     */
    private String adcode;

    /**
     * Country name
     */
    private String country;
}
