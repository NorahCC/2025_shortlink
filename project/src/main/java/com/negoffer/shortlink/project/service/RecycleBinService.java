package com.negoffer.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.negoffer.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import com.negoffer.shortlink.project.dto.req.RecycleBinRemoveReqDTO;
import com.negoffer.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
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
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam);

    /**
     * Restore Short Link from Recycle Bin
     *
     * @param requestParam short link restore request parameters
     */
    void recoverRecycleBin(RecycleBinRecoverReqDTO requestParam);


    /**
     * Removes a short link from the recycle bin.
     *
     * @param requestParam the request parameters for removing the short link
     */
    void removeRecycleBin(RecycleBinRemoveReqDTO requestParam);
}