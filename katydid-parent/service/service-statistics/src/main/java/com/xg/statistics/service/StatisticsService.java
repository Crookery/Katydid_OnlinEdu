package com.xg.statistics.service;

import com.xg.statistics.entity.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-04-12
 */
public interface StatisticsService extends IService<Statistics> {

    void registerCountSave(String day);

    /**
     * 图表显示
     * @param type  查询类型
     * @param begin 开始时间
     * @param end   结束时间
     * @return  日期json数组，数据json数据
     */
    Map<String, Object> getShowData(String type, String begin, String end);
}
