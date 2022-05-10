package com.xg.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.commonutils.Message;
import com.xg.edu.entity.Course;
import com.xg.edu.entity.Teacher;
import com.xg.edu.entity.vo.CourseInfo;
import com.xg.edu.entity.vo.CoursePublishInfoVo;
import com.xg.edu.entity.vo.CourseQuery;
import com.xg.edu.entity.vo.TeacherQuery;
import com.xg.edu.service.CourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */

@RestController
@RequestMapping("/edu/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("添加课程内容和课程简介")
    @PostMapping("/addCourseInfo")
    public Message addCourse(@RequestBody CourseInfo courseInfo){
        String s = courseService.saveCourseInfo(courseInfo);
        return Message.successful().add("courseID",s);
    }

    @ApiOperation("获取课程信息")
    @GetMapping("/getCourseInfo/{courseID}")
    public Message getCourseInfo(@PathVariable String  courseID){
        CourseInfo courseInfo = courseService.getCourseInfo(courseID);
        return Message.successful().add("courseInfo",courseInfo);
    }

    //注意前端验证
    @ApiOperation("修改课程信息")
    @PostMapping("/updateCourseInfo")
    public Message updateCourseInfo(@RequestBody CourseInfo courseInfo){
        return courseService.updateCourseInfo(courseInfo)?Message.successful():Message.fail();
    }

    //课程验证信息
    @GetMapping("/publishCourseInfo/{id}")
    public Message getPublishCourseInfo(@PathVariable String id){
        CoursePublishInfoVo publishCourseInfo = courseService.getPublishCourseInfo(id);
        return Message.successful().add("publishInfo",publishCourseInfo);
    }

    //最终发布课程
    @PutMapping("/publishCourse/{id}")
    public Message publishCourse(@PathVariable String id){
        Course course= new Course();
        course.setId(id);
        course.setStatus(Course.COURSE_STATUTE_ON);
        courseService.updateById(course);
        return Message.successful();
    }

    @ApiOperation("条件分页查询")
    @PostMapping("/pageCourseByCondition/{current}/{limit}")
    public Message pageTeacherByCondition(@PathVariable("current") long current,
                                          @PathVariable("limit") long limit,
                                          @RequestBody(required = false) CourseQuery courseQuery){
        Page<Course> page = courseService.pageByCondition(current, limit, courseQuery);
        return Message.successful().add("page",page);
    }

    @ApiOperation("分页查询")
    @GetMapping("/pageCourse/{current}/{limit}")
    public Message pageTeacherByCondition(@PathVariable("current") long current,
                                          @PathVariable("limit") long limit){
        Page<Course> page = new Page<>(current,limit);
        courseService.page(page,null);
        return Message.successful().add("page",page);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/delCourse/{courseId}")
    public Message delCourse(@PathVariable String courseId){
        return courseService.delCourseById(courseId)?Message.successful():Message.fail();
    }
}

