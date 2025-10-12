package com.negoffer.shortlink.project.service;

/**
 * URL Title Service Interface
 */
public interface UrlTitleService {

    /**
     * Retrieve the title of a website based on its URL.
     *
     * @param url The target website URL.
     * @return The title of the website.
     */
    String getTitleByUrl(String url);
}
