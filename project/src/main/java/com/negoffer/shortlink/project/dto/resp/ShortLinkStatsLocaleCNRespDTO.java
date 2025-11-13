package com.negoffer.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for short link regional statistics
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsLocaleCNRespDTO {

    /**
     * Count
     */
    private Integer cnt;

    /**
     * Region
     */
    private String locale;

    /**
     * Ratio
     */
    private Double ratio;
}
