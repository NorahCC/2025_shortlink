package com.negoffer.shortlink.project.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Short link creation response object
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
     * Full short link
     */
    private String fullShortUrl;
}
