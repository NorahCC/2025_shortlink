package com.negoffer.shortlink.admin.remote;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.dto.req.RecycleBinSaveReqDTO;
import com.negoffer.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.negoffer.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.negoffer.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.negoffer.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.negoffer.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.negoffer.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.negoffer.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Short Link Remote Service
 */
public interface ShortLinkRemoteService {

    /**
     * Create short link
     *
     * @param requestParam Parameters for creating a short link
     * @return Response containing the created short link
     */
    default Result<ShortLinkCreateRespDTO> createShortLink(ShortLinkCreateReqDTO requestParam) {
        String resultBodyStr = HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/create", JSON.toJSONString(requestParam));
        return JSON.parseObject(resultBodyStr, new TypeReference<>() {
        });
    }

    /**
     * Paginated query for short links
     *
     * @param requestParam Parameters for paginated short link query
     * @return Response containing paginated short link results
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gid", requestParam.getGid());
        requestMap.put("current", requestParam.getCurrent());
        requestMap.put("size", requestParam.getSize());
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/page", requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }

    /**
     * Query the total number of short links in each group
     *
     * @param requestParam Request parameters for querying the total number of short links per group
     * @return Response containing the total number of short links per group
     */
    default Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(List<String> requestParam) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("requestParam", requestParam);
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/count", requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }

    /**
     * Update a short link
     *
     * @param requestParam the request parameters for updating the short link
     */
    default void updateShortLink(ShortLinkUpdateReqDTO requestParam) {
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/update", JSON.toJSONString(requestParam));
    }

    /**
     * Get the title of a webpage based on its URL.
     *
     * @param url The target website URL.
     * @return The title of the website.
     */
    default Result<String> getTitleByUrl(@RequestParam("url") String url) {
        // Send a GET request to the short-link service to retrieve the title
        String resultStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/title?url=" + url);

        // Parse the response JSON string into a Result<String> object
        return JSON.parseObject(resultStr, new TypeReference<>() {});
    }

    /**
     * Save to Recycle Bin
     *
     * @param requestParam
     */
    default void saveRecycleBin(RecycleBinSaveReqDTO requestParam) {
        HttpUtil.post("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/save", JSON.toJSONString(requestParam));
    }

    /**
     * Paginated query for short links in the recycle bin
     *
     * @param requestParam Request parameters for paginated short link query
     * @return Response containing queried short links
     */
    default Result<IPage<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam) {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("gidList", requestParam.getGidList());
        requestMap.put("current", requestParam.getCurrent());
        requestMap.put("size", requestParam.getSize());
        String resultPageStr = HttpUtil.get("http://127.0.0.1:8001/api/short-link/v1/recycle-bin/page", requestMap);
        return JSON.parseObject(resultPageStr, new TypeReference<>() {
        });
    }
}

