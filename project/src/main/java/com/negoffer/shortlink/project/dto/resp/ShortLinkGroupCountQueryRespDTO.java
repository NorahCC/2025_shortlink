package com.negoffer.shortlink.project.dto.resp;

import lombok.Data;

/**
 * Response DTO for short link group count query
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * Group identifier
     */
    private String gid;

    /**
     * Number of short links in the group
     */
    private Integer shortLinkCount;
}