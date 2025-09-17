package com.negoffer.shortlink.admin.controller;

import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.common.convention.result.Results;
import com.negoffer.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.negoffer.shortlink.admin.dto.req.ShortLinkGroupUpdateReqDTO;
import com.negoffer.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.negoffer.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    /**
     * Add new short-link group
     */
    @PostMapping("/api/short-link/admin/v1/group")
    public Result<Void> save(@RequestBody ShortLinkGroupSaveReqDTO requestParam) {
        groupService.saveGroup(requestParam.getName());
        return Results.success();
    }

    /**
     * Retrieve the list of short link groups
     */
    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

    /**
     * Update the name of a short link group
     */
    @PutMapping("/api/short-link/admin/v1/group")
    public Result<Void> updateGroup(@RequestBody ShortLinkGroupUpdateReqDTO requestParam) {
        groupService.updateGroup(requestParam);
        return Results.success();
    }
}
