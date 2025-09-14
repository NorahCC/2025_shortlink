package com.negoffer.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.negoffer.shortlink.admin.dao.entity.GroupDO;
import com.negoffer.shortlink.admin.dao.mapper.GroupMapper;
import com.negoffer.shortlink.admin.service.GroupService;
import com.negoffer.shortlink.admin.toolkit.RandomGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Short link group service implementation layer
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl extends ServiceImpl<GroupMapper, GroupDO> implements GroupService {
    @Override
    public void saveGroup(String groupName) {
        String gid;
        do{
            gid = RandomGenerator.generateRandom();
        } while(!hasGid(gid));

        GroupDO groupDO = GroupDO.builder()
                .gid(RandomGenerator.generateRandom())
                .name(groupName)
                .build();

        baseMapper.insert(groupDO);


    }

    private boolean hasGid(String gid) {
        LambdaQueryWrapper<GroupDO> queryWrapper = Wrappers.lambdaQuery(GroupDO.class)
                .eq(GroupDO::getGid, gid)
                // TODO set username
                .eq(GroupDO:: getUsername, null);
        GroupDO hasGroupFlag = baseMapper.selectOne(queryWrapper);
        return hasGroupFlag != null;
    }


}
