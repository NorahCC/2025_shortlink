package com.negoffer.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.common.convention.result.Results;
import com.negoffer.shortlink.admin.dto.req.RecycleBinRecoverReqDTO;
import com.negoffer.shortlink.admin.dto.req.RecycleBinRemoveReqDTO;
import com.negoffer.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import com.negoffer.shortlink.admin.remote.ShortLinkRemoteService;
import com.negoffer.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.negoffer.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.negoffer.shortlink.admin.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Recycle Bin Controller Layer
 */
@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    /**
     * TODO: Refactor to use SpringCloud Feign for remote call
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * Save to Recycle Bin
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        shortLinkRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * paginated query for short links in recycle bin
     */
    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return recycleBinService.pageRecycleBinShortLink(requestParam);
    }

    /**
     * Restore a short link from the recycle bin
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam) {
        shortLinkRemoteService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * Remove short link
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        shortLinkRemoteService.removeRecycleBin(requestParam);
        return Results.success();
    }
}