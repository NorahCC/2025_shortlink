package com.negoffer.shortlink.project.dto.resp;

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
    private Date validDate;

    /**
     * Description or remarks for the short link
     */
    private String describe;

    /**
     * Website favicon or logo URL
     */
    private String favicon;
}
