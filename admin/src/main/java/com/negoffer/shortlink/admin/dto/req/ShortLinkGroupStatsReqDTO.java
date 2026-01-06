package com.negoffer.shortlink.admin.dto.req;

import lombok.Data;

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
