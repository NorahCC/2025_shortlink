package com.negoffer.shortlink.project.dto.req;

import lombok.Data;

/**
 * Recycle Bin Save Function
 */
@Data
public class RecycleBinSaveReqDTO {

    /**
     * Group Identifier
     */
    private String gid;

    /**
     * All Short Links
     */
    private String fullShortUrl;
}