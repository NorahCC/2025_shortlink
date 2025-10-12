package com.negoffer.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.negoffer.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * Recycle Bin Service Interface
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * Save to Recycle Bin
     *
     * @param requestParam
     */
    void saveRecycleBin(RecycleBinSaveReqDTO requestParam);

    /**
     * Paginated query for short links in the recycle bin
     *
     * @param requestParam Request parameters for paginated short link query
     * @return Response containing queried short links
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);
}