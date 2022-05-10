package com.xg.statistics.controller;


import com.xg.commonutils.Message;
import com.xg.statistics.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-04-12
 */

@RestController
@RequestMapping("/statistics/daily")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiOperation("统计某天注册人数，生成统计数据")
    @PostMapping("registerCount/{day}")
    public Message registerCount(@PathVariable("day") String day){
        statisticsService.registerCountSave(day);
        return Message.successful();
    }

    @ApiOperation("显示统计数据")
    @GetMapping("showData/{type}/{begin}/{end}")
    public Message showData(@PathVariable("type") String type,
                            @PathVariable("begin") String begin,
                            @PathVariable("end") String end){
        Map<String,Object> map = statisticsService.getShowData(type,begin,end);
        return Message.successful().add("res",map);
    }

}

