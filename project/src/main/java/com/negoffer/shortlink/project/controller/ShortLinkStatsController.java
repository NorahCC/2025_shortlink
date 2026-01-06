package com.negoffer.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.negoffer.shortlink.project.common.convention.result.Result;
import com.negoffer.shortlink.project.common.convention.result.Results;
import com.negoffer.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkStatsRespDTO;
import com.negoffer.shortlink.project.service.ShortLinkStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for short link monitoring
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {

    private final ShortLinkStatsService shortLinkStatsService;

    /**
     * Retrieve monitoring data for a specific short link within a given time period
     */
    @GetMapping("/api/short-link/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.oneShortLinkStats(requestParam));
    }

    /**
     * Retrieves monitoring statistics for grouped short links within a specified time range.
     */
    @GetMapping("/api/short-link/v1/stats/group")
    public Result<ShortLinkStatsRespDTO> groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam) {
        return Results.success(shortLinkStatsService.groupShortLinkStats(requestParam));
    }

    /**
     * Retrieves access record monitoring data for a single short link within a specified time range.
     */
    @GetMapping("/api/short-link/v1/stats/access-record")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.shortLinkStatsAccessRecord(requestParam));
    }

    /**
     * 访问分组短链接指定时间内访问记录监控数据
     */
    @GetMapping("/api/short-link/v1/stats/access-record/group")
    public Result<IPage<ShortLinkStatsAccessRecordRespDTO>> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam) {
        return Results.success(shortLinkStatsService.groupShortLinkStatsAccessRecord(requestParam));
    }
}
