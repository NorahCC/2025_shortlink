package com.negoffer.shortlink.project.dto.req;

import lombok.Data;

/**
 * Grouped short link monitoring request parameters
 */
@Data
public class ShortLinkGroupStatsReqDTO {

    /**
     * Group Identifier
     */
    private String gid;

    /**
     * Start Date
     */
    private String startDate;

    /**
     * End Date
     */
    private String endDate;
}
