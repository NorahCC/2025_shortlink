package com.negoffer.shortlink.project.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Short link creation request object
 */
@Data
public class ShortLinkCreateReqDTO {

    /**
     * Protocol
     */
    private String domainProtocol;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * Description
     */
    private String describe;
}
