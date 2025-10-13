package com.negoffer.shortlink.project.dto.req;

import lombok.Data;

/**
 * Recycle Bin Restore Function
 */
@Data
public class RecycleBinRecoverReqDTO {

    /**
     * Group Identifier
     */
    private String gid;

    /**
     * All Short Links
     */
    private String fullShortUrl;
}