package com.negoffer.shortlink.project.controller;

import com.negoffer.shortlink.project.common.convention.result.Result;
import com.negoffer.shortlink.project.common.convention.result.Results;
import com.negoffer.shortlink.project.service.UrlTitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for retrieving the title of a given URL.
 */
@RestController
@RequiredArgsConstructor
public class UrlTitleController {

    private final UrlTitleService urlTitleService;

    /**
     * Retrieve the title of a website based on its URL.
     *
     * @param url The target website URL.
     * @return The title of the website.
     */
    @GetMapping("/api/short-link/v1/title")
    public Result<String> getTitleByUrl(@RequestParam("url") String url) {
        return Results.success(urlTitleService.getTitleByUrl(url));
    }
}
