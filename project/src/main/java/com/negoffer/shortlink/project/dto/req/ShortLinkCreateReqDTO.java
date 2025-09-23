package com.negoffer.shortlink.project.dto.req;

import lombok.Data;

import java.util.Date;

/**
 * Short link creation request object
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
     * Group identifier
     */
    private String gid;

    /**
     * Creation type: 0 = created via API, 1 = created via console
     */
    private Integer createdType;

    /**
     * Validity type: 0 = permanent, 1 = custom
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
