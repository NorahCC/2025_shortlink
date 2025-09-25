package com.negoffer.shortlink.admin.remote.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short Link Creation Response DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShortLinkCreateRespDTO {

    /**
     * Group identifier
     */
    private String gid;

    /**
     * Original URL
     */
    private String originUrl;

    /**
     * Full short URL
     */
    private String fullShortUrl;
}
