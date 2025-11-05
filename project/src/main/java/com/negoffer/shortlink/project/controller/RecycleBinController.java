package com.negoffer.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.negoffer.shortlink.project.common.convention.result.Result;
import com.negoffer.shortlink.project.common.convention.result.Results;
import com.negoffer.shortlink.project.dto.req.RecycleBinRecoverReqDTO;
import com.negoffer.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.negoffer.shortlink.project.dto.req.RecycleBinRemoveReqDTO;
import com.negoffer.shortlink.project.service.RecycleBinService;
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
     * Save to Recycle Bin
     */
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * Paginated query for short links in the recycle bin
     */
    @GetMapping("/api/short-link/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        return Results.success(recycleBinService.pageShortLink(requestParam));
    }

    /**
     * Restore a short link from the recycle bin
     */
    @PostMapping("/api/short-link/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody RecycleBinRecoverReqDTO requestParam) {
        recycleBinService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * Remove short link
     */
    @PostMapping("/api/short-link/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody RecycleBinRemoveReqDTO requestParam) {
        recycleBinService.removeRecycleBin(requestParam);
        return Results.success();
    }
}