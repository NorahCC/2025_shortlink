package com.negoffer.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response DTO for short link visitor statistics
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkStatsUvRespDTO {

    /**
     * Count
     */
    private Integer cnt;

    /**
     * Visitor type
     */
    private String uvType;

    /**
     * Ratio
     */
    private Double ratio;
}
