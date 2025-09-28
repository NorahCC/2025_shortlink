package com.negoffer.shortlink.admin.remote.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Short link update request object
 */
@Data
public class ShortLinkUpdateReqDTO {

    /**
     * Original URL
     */
    private String originUrl;

    /**
     * Full short link
     */
    private String fullShortUrl;

    /**
     * Group identifier
     */
    private String gid;

    /**
     * Expiration type: 0 - permanent, 1 - custom
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
