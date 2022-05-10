package com.xg.cms.service;

import com.xg.cms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-03-27
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 查询所有Banner
     */
    List<CrmBanner> getAllBanner();

}
