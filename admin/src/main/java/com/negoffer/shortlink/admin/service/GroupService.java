package com.negoffer.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.admin.dao.entity.GroupDO;
import com.negoffer.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;

import java.util.List;

/**
 * Short link group service interface layer
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * Add a new short link group
     *
     * @param groupName Name of the short link group
     */
    void saveGroup(String groupName);

    /**
     * Query the list of short link groups for the user
     *
     * @return List of the user's short link groups
     */
    List<ShortLinkGroupRespDTO> listGroup();

}
