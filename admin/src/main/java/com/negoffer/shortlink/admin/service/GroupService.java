package com.negoffer.shortlink.admin.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.negoffer.shortlink.admin.dao.entity.GroupDO;

/**
 * 短链接分组接口层
 * 公众号：马丁玩编程，回复：加群，添加马哥微信（备注：link）获取项目资料
 */
public interface GroupService extends IService<GroupDO> {
    /**
     * 新增短链接分组
     *
     * @param groupName 短链接分组名
     */
    void saveGroup(String groupName);

}