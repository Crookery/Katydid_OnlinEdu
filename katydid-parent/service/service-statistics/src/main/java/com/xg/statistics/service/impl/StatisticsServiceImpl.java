package com.xg.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.commonutils.Message;
import com.xg.servicebase.exception.KatyException;
import com.xg.statistics.client.UCenterClient;
import com.xg.statistics.entity.Statistics;
import com.xg.statistics.mapper.StatisticsMapper;
import com.xg.statistics.service.StatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-04-12
 */
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics> implements StatisticsService {
    @Autowired
    private UCenterClient uCenterClient;

    @Override
    public void registerCountSave(String day) {
        Message message = uCenterClient.countRegister(day);
        Integer count = (Integer) message.getData().get("count");
        if(count== null){
            throw new KatyException(500,"远程调用出错");
        }
        //讲获取的数据存储到表中
        Statistics statistics = new Statistics();
        statistics.setDateCalculated(day);
        statistics.setRegisterNum(count);
        Random r = new Random();
        statistics.setLoginNum(r.nextInt(100));
        statistics.setVideoViewNum(r.nextInt(100));
        statistics.setCourseNum(r.nextInt(10));

        baseMapper.insert(statistics);
    }



    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<Statistics> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        //指定查询的列
        wrapper.select("date_calculated",type);

        List<Statistics> list = baseMapper.selectList(wrapper);

        //list=>JSON数组
        List<String> date = new LinkedList<>();         //日期数据
        List<Integer> numDataList = new LinkedList<>(); //数量

        //TODO 通过反射进行改进
        for(Statistics s:list){
            date.add(s.getDateCalculated());
            switch (type) {
                case "register_num":
                    numDataList.add(s.getRegisterNum());
                    break;
                case "login_num":
                    numDataList.add(s.getLoginNum());
                    break;
                case "video_view_num":
                    numDataList.add(s.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(s.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        Map<String,Object> res = new HashMap<>();
        res.put("date",date);
        res.put("num",numDataList);
        return res;
    }
}
