package com.negoffer.shortlink.project.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.negoffer.shortlink.project.common.convention.result.Result;
import com.negoffer.shortlink.project.common.convention.result.Results;
import com.negoffer.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.negoffer.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Short link controller
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    private final ShortLinkService shortLinkService;

    /**
     * Create a short link
     */
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }

    /**
     * Paginated query for short links
     */
    @GetMapping("/api/short-link/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return Results.success(shortLinkService.pageShortLink(requestParam));
    }
}
