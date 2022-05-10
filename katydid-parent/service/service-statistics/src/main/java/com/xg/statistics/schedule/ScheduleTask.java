package com.xg.statistics.schedule;

import com.xg.statistics.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduleTask {

    @Autowired
    private StatisticsService statisticsService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void statistic(){
        statisticsService.registerCountSave(LocalDate.now().plusDays(-1).toString());
    }


}
