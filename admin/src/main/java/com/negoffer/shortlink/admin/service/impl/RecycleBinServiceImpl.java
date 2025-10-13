package com.negoffer.shortlink.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.negoffer.shortlink.admin.common.biz.user.UserContext;
import com.negoffer.shortlink.admin.common.convention.exception.ServiceException;
import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.dao.entity.GroupDO;
import com.negoffer.shortlink.admin.dao.mapper.GroupMapper;
import com.negoffer.shortlink.admin.remote.ShortLinkRemoteService;
import com.negoffer.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.negoffer.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.negoffer.shortlink.admin.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * URL Recycle Bin Service Implementation Layer
 */
@Service
@RequiredArgsConstructor
public class RecycleBinServiceImpl implements RecycleBinService {

    private final GroupMapper groupMapper;

    /**
     * To be refactored into a SpringCloud Feign call in the future
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    @Override
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getUsername, UserContext.getUsername())
                .eq(GroupDO::getDelFlag, 0);
        List<GroupDO> groupDOList = groupMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(groupDOList)) {
            throw new ServiceException("User has no group information");
        }
        requestParam.setGidList(groupDOList.stream().map(GroupDO::getGid).toList());
        return shortLinkRemoteService.pageRecycleBinShortLink(requestParam);
    }
}