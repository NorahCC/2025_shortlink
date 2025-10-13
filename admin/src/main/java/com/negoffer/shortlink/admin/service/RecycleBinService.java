package com.negoffer.shortlink.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.negoffer.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * URL Recycle Bin Service Interface
 */
public interface RecycleBinService {

    /**
     * Paginated query for short links in the recycle bin
     *
     * @param requestParam Request parameters
     * @return Wrapped response with pagination
     */
    Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}