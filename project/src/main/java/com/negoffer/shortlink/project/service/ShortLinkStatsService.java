package com.negoffer.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.negoffer.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * Service interface for short link monitoring
 */
public interface ShortLinkStatsService {

    /**
     * Retrieve monitoring data for a single short link
     *
     * @param requestParam Request parameters for retrieving short link monitoring data
     * @return Short link monitoring data
     */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);

    /**
     * 访问单个短链接指定时间内访问记录监控数据
     *
     * @param requestParam 获取短链接监控访问记录数据入参
     * @return 访问记录监控数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam);
}
