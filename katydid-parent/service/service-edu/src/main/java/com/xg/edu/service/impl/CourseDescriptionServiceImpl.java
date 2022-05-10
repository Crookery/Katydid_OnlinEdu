package com.xg.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.edu.entity.CourseDescription;
import com.xg.edu.entity.Video;
import com.xg.edu.mapper.CourseDescriptionMapper;
import com.xg.edu.service.CourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.servicebase.exception.KatyException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

    @Override
    public void delDescription(String courseId) {
        QueryWrapper<CourseDescription> wrapper = new QueryWrapper<>();

        if(courseId != null){
            wrapper.eq("id",courseId);
        }else{
            throw new KatyException(500,"参数异常,为空");
        }

        baseMapper.delete(wrapper);
    }
}
