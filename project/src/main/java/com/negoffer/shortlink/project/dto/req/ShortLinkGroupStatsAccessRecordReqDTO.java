package com.negoffer.shortlink.project.dto.req;

import com.negoffer.shortlink.project.dao.entity.LinkAccessLogsDO;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * Request parameters for grouped short link monitoring access records
 */
@Data
public class ShortLinkGroupStatsAccessRecordReqDTO extends Page<LinkAccessLogsDO> {

    /**
     * Group Identifier
     */
    private String gid;

    /**
     * Start Date
     */
    private String startDate;

    /**
     * End Date
     */
    private String endDate;
}