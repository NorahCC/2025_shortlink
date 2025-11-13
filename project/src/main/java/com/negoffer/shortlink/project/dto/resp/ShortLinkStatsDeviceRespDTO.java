package com.negoffer.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for short link device access statistics
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsDeviceRespDTO {

    /**
     * Count
     */
    private Integer cnt;

    /**
     * Device type
     */
    private String device;

    /**
     * Ratio
     */
    private Double ratio;
}
