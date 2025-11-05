package com.negoffer.shortlink.project.dto.req;

import lombok.Data;

/**
 * Recycle Bin Remove Function
 */
@Data
public class RecycleBinRemoveReqDTO {

    /**
     * Group Identifier
     */
    private String gid;

    /**
     * All Short Links
     */
    private String fullShortUrl;
}