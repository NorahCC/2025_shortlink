package com.negoffer.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for short link operating system statistics
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsOsRespDTO {

    /**
     * Count
     */
    private Integer cnt;

    /**
     * Operating system
     */
    private String os;

    /**
     * Ratio
     */
    private Double ratio;
}
