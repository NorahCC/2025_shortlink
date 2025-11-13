package com.negoffer.shortlink.project.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Response DTO for basic short link access monitoring
 */
@Data
public class ShortLinkStatsAccessDailyRespDTO {

    /**
     * Date
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date date;

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
}
