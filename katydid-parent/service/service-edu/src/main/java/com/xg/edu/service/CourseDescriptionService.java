package com.xg.edu.service;

import com.xg.edu.entity.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
public interface CourseDescriptionService extends IService<CourseDescription> {

    /**
     * 根据课程id，删除课程简介
     */
    void delDescription(String courseId);

}
