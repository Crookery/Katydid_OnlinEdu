package com.xg.edu.mapper;

import com.xg.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xg.edu.entity.vo.CoursePublishInfoVo;
import com.xg.edu.entity.vo.front.FrontCourseDetailVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
public interface CourseMapper extends BaseMapper<Course> {
    CoursePublishInfoVo getCoursePublishInfoVo(String courseId);

    /**
     * 前台：根据id获取课程的详细信息
     * @param id 课程id
     */
    FrontCourseDetailVo getFrontCourseDetail(String id);
}
