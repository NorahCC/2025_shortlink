package com.negoffer.shortlink.project.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.negoffer.shortlink.project.common.database.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * short link entity
 */
@Data
@Builder
@TableName("t_link")
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkDO extends BaseDO {

    /**
     * ID
     */
    private Long id;

    /**
     * Domain
     */
    private String domain;

    /**
     * Short URI
     */
    private String shortUri;

    /**
     * Full short URL
     */
    private String fullShortUrl;

    /**
     * Original URL
     */
    private String originUrl;

    /**
     * Click count
     */
    private Integer clickNum;

    /**
     * Group identifier
     */
    private String gid;

    /**
     * Enable status: 0 = enabled, 1 = disabled
     */
    private Integer enableStatus;

    /**
     * Creation type: 0 = API, 1 = Console
     */
    private Integer createdType;

    /**
     * Validity type: 0 = permanent, 1 = custom
     */
    private Integer validDateType;

    /**
     * Expiration date
     */
    private Date validDate;

    /**
     * Description
     */
    @TableField("`describe`")
    private String describe;
}
