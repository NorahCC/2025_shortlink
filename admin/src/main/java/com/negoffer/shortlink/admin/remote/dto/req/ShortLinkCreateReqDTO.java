package com.negoffer.shortlink.admin.remote.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Short Link Creation Request DTO
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
