package com.negoffer.shortlink.admin.remote.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Short Link Pagination Response DTO
 */
@Data
public class ShortLinkPageRespDTO {

    /**
     * ID
     */
    private Long id;

    /**
     * Domain
     */
    private String domain;

    /**
     * Short URI
     */
    private String shortUri;

    /**
     * Full short URL
     */
    private String fullShortUrl;

    /**
     * Original URL
     */
    private String originUrl;

    /**
     * Group identifier
     */
    private String gid;

    /**
     * Validity type: 0 - permanent, 1 - custom
     */
    private Integer validDateType;

    /**
     * Valid until
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date validDate;

    /**
     * Creation time
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * Description
     */
    private String describe;

    /**
     * Website icon
     */
    private String favicon;
}
