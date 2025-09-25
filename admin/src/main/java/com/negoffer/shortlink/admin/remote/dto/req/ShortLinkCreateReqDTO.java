package com.negoffer.shortlink.admin.remote.dto.req;

import lombok.Data;

import java.util.Date;

/**
 * Short Link Creation Request DTO
 */
@Data
public class ShortLinkCreateReqDTO {

    /**
     * Domain
     */
    private String domain;

    /**
     * Original URL
     */
    private String originUrl;

    /**
     * Group ID
     */
    private String gid;

    /**
     * Creation type: 0 - API, 1 - Console
     */
    private Integer createdType;

    /**
     * Expiration type: 0 - Permanent, 1 - Custom
     */
    private Integer validDateType;

    /**
     * Expiration date
     */
    private Date validDate;

    /**
     * Description
     */
    private String describe;
}
