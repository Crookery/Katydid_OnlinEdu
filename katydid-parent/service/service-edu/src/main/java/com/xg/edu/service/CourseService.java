package com.xg.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.edu.entity.vo.CourseInfo;
import com.xg.edu.entity.vo.CoursePublishInfoVo;
import com.xg.edu.entity.vo.CourseQuery;
import com.xg.edu.entity.vo.front.FrontCourseDetailVo;
import com.xg.edu.entity.vo.front.FrontCourseSearchVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
public interface CourseService extends IService<Course> {

    /**
     * 通过courseInfo信息，添加实体course和 courseDescription
     */
    String saveCourseInfo(CourseInfo courseInfo);

    /**
     * 查询课程表信息（包括课程和简介）
     */
    CourseInfo getCourseInfo(String courseID);

    boolean updateCourseInfo(CourseInfo courseInfo);

    /**
     * 最终确认发布信息
     */
    CoursePublishInfoVo getPublishCourseInfo(String id);

    /**
     * 根据条件分页查询
     */
    Page<Course> pageByCondition(long current, long limit, CourseQuery cq);

    /**
     * 根据课程id，删除整个课程
     */
    boolean delCourseById(String courseId);

    /**
     * 返回热门课程数据
     * @return 按查阅量从大到小排序
     */
    List<Course> getHotCourseData();

    /**
     * 前台，条件查询带分页
     */
    Map<String, Object> frontCourseList(Page<Course> coursePage, FrontCourseSearchVo frontCourseSearchVo);

    /**
     * 前台：根据id获取课程的详细信息
     * @param id 课程id
     */
    FrontCourseDetailVo getFrontCourseDetail(String id);
}
