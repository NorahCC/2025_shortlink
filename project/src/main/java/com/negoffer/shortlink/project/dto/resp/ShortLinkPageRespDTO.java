package com.negoffer.shortlink.project.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Short Link Pagination Response DTO
 */
@Data
public class ShortLinkPageRespDTO {

    /**
     * Primary key ID
     */
    private Long id;

    /**
     * Domain of the short link
     */
    private String domain;

    /**
     * Short URI suffix
     */
    private String shortUri;

    /**
     * Full short link (including domain)
     */
    private String fullShortUrl;

    /**
     * Original URL before shortening
     */
    private String originUrl;

    /**
     * Group identifier (e.g., to organize by user/group)
     */
    private String gid;

    /**
     * Validity type: 0 = permanent, 1 = custom expiration
     */
    private Integer validDateType;

    /**
     * Expiration date (if applicable)
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * Create time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * Description or remarks for the short link
     */
    private String describe;

    /**
     * Website favicon or logo URL
     */
    private String favicon;

    /**
     * Historical PV (Page Views)
     */
    private Integer totalPv;

    /**
     * Today's PV (Page Views)
     */
    private Integer toDayPv;

    /**
     * Historical UV (Unique Visitors)
     */
    private Integer totalUv;

    /**
     * Today's UV (Unique Visitors)
     */
    private Integer toDayUv;

    /**
     * Historical UIP (Unique IPs)
     */
    private Integer totalUIp;

    /**
     * Today's UIP (Unique IPs)
     */
    private Integer toDayUIp;

}
