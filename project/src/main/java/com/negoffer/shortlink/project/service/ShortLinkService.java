package com.negoffer.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.negoffer.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * Short link service interface
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * Create a short link
     *
     * @param requestParam Parameters for short link creation
     * @return Information about the created short link
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /**
     * Paginated query for short links.
     *
     * @param requestParam the request parameters for paginated short link query
     * @return paginated result of short links
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}