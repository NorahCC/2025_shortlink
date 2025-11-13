package com.negoffer.shortlink.project.service;

import com.negoffer.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * Service interface for short link monitoring
 */
public interface ShortLinkStatsService {

    /**
     * Retrieve monitoring data for a single short link
     *
     * @param requestParam Request parameters for retrieving short link monitoring data
     * @return Short link monitoring data
     */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);
}
