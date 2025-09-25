package com.negoffer.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Short Link Pagination Request DTO
 */
@Data
public class ShortLinkPageReqDTO extends Page {

    /**
     * Group ID
     */
    private String gid;
}