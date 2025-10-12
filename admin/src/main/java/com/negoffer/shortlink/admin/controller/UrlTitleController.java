package com.negoffer.shortlink.admin.controller;

import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.remote.ShortLinkRemoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * URL Title Controller
 */
@RestController
@RequiredArgsConstructor
public class UrlTitleController {

    /**
     * TODO: Refactor to use SpringCloud Feign for remote service calls
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * Get the title of a website by URL
     *
     * @param url The target website URL
     * @return The title of the web page
     */
    @GetMapping("/api/short-link/admin/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        return shortLinkRemoteService.getTitleByUrl(url);
    }
}
