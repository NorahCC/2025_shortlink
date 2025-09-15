package com.negoffer.shortlink.admin.controller;

import com.negoffer.shortlink.admin.common.convention.result.Result;
import com.negoffer.shortlink.admin.common.convention.result.Results;
import com.negoffer.shortlink.admin.dto.req.ShortLinkGroupSaveReqDTO;
import com.negoffer.shortlink.admin.dto.resp.ShortLinkGroupRespDTO;
import com.negoffer.shortlink.admin.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
     * 查询短链接分组集合
     */
    @GetMapping("/api/short-link/admin/v1/group")
    public Result<List<ShortLinkGroupRespDTO>> listGroup() {
        return Results.success(groupService.listGroup());
    }

}
