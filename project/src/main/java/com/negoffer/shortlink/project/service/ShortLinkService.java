package com.negoffer.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.negoffer.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkPageRespDTO;

import java.util.List;

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
     * Update a short link
     *
     * @param requestParam Short link update request parameters
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * Paginated query for short links.
     *
     * @param requestParam the request parameters for paginated short link query
     * @return paginated result of short links
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * Query the number of short links within each group
     *
     * @param requestParam Request parameters for querying the number of short links per group
     * @return Response containing the number of short links per group
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);
}