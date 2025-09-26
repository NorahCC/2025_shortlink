package com.negoffer.shortlink.project.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.text.StrBuilder;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.negoffer.shortlink.project.common.convention.exception.ServiceException;
import com.negoffer.shortlink.project.dao.entity.ShortLinkDO;
import com.negoffer.shortlink.project.dao.mapper.ShortLinkMapper;
import com.negoffer.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.negoffer.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.negoffer.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import com.negoffer.shortlink.project.service.ShortLinkService;
import com.negoffer.shortlink.project.toolkit.HashUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Implementation layer for short link service
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {

    /**
     * Bloom filter to prevent cache penetration during short link creation
     */
    private final RBloomFilter<String> shortUriCreateCachePenetrationBloomFilter;
    private final ShortLinkMapper shortLinkMapper;

    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam) {
        // Generate short URI suffix
        String shortLinkSuffix = generateSuffix(requestParam);

        // Assemble full short URL
        String fullShortUrl = StrBuilder.create(requestParam.getDomain())
                .append("/")
                .append(shortLinkSuffix)
                .toString();

        // Build short link entity
        ShortLinkDO shortLinkDO = ShortLinkDO.builder()
                .domain(requestParam.getDomain())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .createdType(requestParam.getCreatedType())
                .validDateType(requestParam.getValidDateType())
                .validDate(requestParam.getValidDate())
                .describe(requestParam.getDescribe())
                .shortUri(shortLinkSuffix)
                .enableStatus(0)
                .fullShortUrl(fullShortUrl)
                .build();

        // Try to insert into DB, handle duplicate cases
        try {
            baseMapper.insert(shortLinkDO);
        } catch (DuplicateKeyException ex) {
            LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl);
            ShortLinkDO hasShortLinkDO = baseMapper.selectOne(queryWrapper);
            if (hasShortLinkDO != null) {
                log.warn("Short link {} already exists in DB", fullShortUrl);
                throw new ServiceException("Short link already exists");
            }
        }

        // Add generated URI to bloom filter to avoid future duplicate generation
        shortUriCreateCachePenetrationBloomFilter.add(shortLinkSuffix);

        // Return response
        return ShortLinkCreateRespDTO.builder()
                .fullShortUrl(shortLinkDO.getFullShortUrl())
                .originUrl(requestParam.getOriginUrl())
                .gid(requestParam.getGid())
                .build();
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, requestParam.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0)
                .eq(ShortLinkDO::getDelFlag, 0)
                .orderByDesc(ShortLinkDO::getCreateTime);
        IPage<ShortLinkDO> resultPage = baseMapper.selectPage(requestParam, queryWrapper);
        return resultPage.convert(each -> BeanUtil.toBean(each, ShortLinkPageRespDTO.class));
    }

    @Override
    public List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam) {
        QueryWrapper<ShortLinkDO> queryWrapper = Wrappers.query(new ShortLinkDO())
                .select("gid as gid, count(*) as shortLinkCount")
                .in("gid", requestParam)
                .eq("enable_status", 0)
                .groupBy("gid");
        List<Map<String, Object>> shortLinkDOList = baseMapper.selectMaps(queryWrapper);
        return BeanUtil.copyToList(shortLinkDOList, ShortLinkGroupCountQueryRespDTO.class);
    }

    /**
     * Generate a unique short URI suffix
     * Retries up to 10 times in case of collision
     */
    private String generateSuffix(ShortLinkCreateReqDTO requestParam) {
        int customGenerateCount = 0;
        String shortUri;

        while (true) {
            if (customGenerateCount > 10) {
                throw new ServiceException("Short link generation is too frequent, please try again later.");
            }

            String originUrl = requestParam.getOriginUrl() + System.currentTimeMillis();
            shortUri = HashUtil.hashToBase62(originUrl);

            // Check bloom filter to avoid collision
            if (!shortUriCreateCachePenetrationBloomFilter.contains(requestParam.getDomain() + "/" + shortUri)) {
                break;
            }
            customGenerateCount++;
        }

        return shortUri;
    }
}
