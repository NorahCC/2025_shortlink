package com.negoffer.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * Pagination request parameters for recycle bin short links
 */
@Data
public class ShortLinkRecycleBinPageReqDTO extends Page {

    /**
     * Group identifier
     */
    private List<String> gidList;
}