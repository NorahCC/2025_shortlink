package com.negoffer.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.negoffer.shortlink.project.dto.req.RecycleBinSaveReqDTO;

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
}