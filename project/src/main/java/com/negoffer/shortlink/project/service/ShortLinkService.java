package com.negoffer.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.negoffer.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkBatchCreateReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkBatchCreateRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

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
     * 批量创建短链接
     *
     * @param requestParam 批量创建短链接请求参数
     * @return 批量创建短链接返回参数
     */
    ShortLinkBatchCreateRespDTO batchCreateShortLink(ShortLinkBatchCreateReqDTO requestParam);

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

    /**
     * Short link redirection
     *
     * @param shortUri  Suffix of the short link
     * @param request   HTTP request
     * @param response  HTTP response
     */
    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response);

    /**
     * 短链接统计
     *
     * @param fullShortUrl         完整短链接
     * @param gid                  分组标识
     * @param shortLinkStatsRecord 短链接统计实体参数
     */
    void shortLinkStats(String fullShortUrl, String gid, ShortLinkStatsRecordDTO shortLinkStatsRecord);
}