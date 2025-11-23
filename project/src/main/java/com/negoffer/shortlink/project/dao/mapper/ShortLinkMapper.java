package com.negoffer.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.negoffer.shortlink.project.dto.req.ShortLinkPageReqDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * Short link persistence layer
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {
    /**
     * Increment short link access statistics
     */
    @Update("UPDATE t_link SET total_pv = total_pv + #{totalPv}, total_uv = total_uv + #{totalUv}, total_uip = total_uip + #{totalUip} " +
            "WHERE gid = #{gid} AND full_short_url = #{fullShortUrl}")
    void incrementStats(
            @Param("gid") String gid,
            @Param("fullShortUrl") String fullShortUrl,
            @Param("totalPv") Integer totalPv,
            @Param("totalUv") Integer totalUv,
            @Param("totalUip") Integer totalUip
    );

    /**
     * Paginated short link statistics
     */
    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);

}
