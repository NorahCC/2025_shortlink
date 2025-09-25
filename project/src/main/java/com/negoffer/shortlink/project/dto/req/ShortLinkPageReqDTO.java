package com.negoffer.shortlink.project.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 * Short link pagination request parameters
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * Group Identifier
     */
    private String gid;
}