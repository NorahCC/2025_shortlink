package com.negoffer.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response DTO for short link monitoring
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsRespDTO {

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
     * Basic daily access details
     */
    private List<ShortLinkStatsAccessDailyRespDTO> daily;

    /**
     * Regional access details (domestic only)
     */
    private List<ShortLinkStatsLocaleCNRespDTO> localeCnStats;

    /**
     * Hourly access details
     */
    private List<Integer> hourStats;

    /**
     * Top frequent IP access details
     */
    private List<ShortLinkStatsTopIpRespDTO> topIpStats;

    /**
     * Weekly access details
     */
    private List<Integer> weekdayStats;

    /**
     * Browser access details
     */
    private List<ShortLinkStatsBrowserRespDTO> browserStats;

    /**
     * Operating system access details
     */
    private List<ShortLinkStatsOsRespDTO> osStats;

    /**
     * Visitor type statistics
     */
    private List<ShortLinkStatsUvRespDTO> uvTypeStats;

    /**
     * Device type access details
     */
    private List<ShortLinkStatsDeviceRespDTO> deviceStats;

    /**
     * Network type access details
     */
    private List<ShortLinkStatsNetworkRespDTO> networkStats;
}
