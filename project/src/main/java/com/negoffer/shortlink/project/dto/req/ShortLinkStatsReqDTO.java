package com.negoffer.shortlink.project.dto.req;

import lombok.Data;

/**
 * Request parameters for short link monitoring
 */
@Data
public class ShortLinkStatsReqDTO {

    /**
     * Full short link
     */
    private String fullShortUrl;

    /**
     * Group identifier
     */
    private String gid;

    /**
     * Start date
     */
    private String startDate;

    /**
     * End date
     */
    private String endDate;
}
