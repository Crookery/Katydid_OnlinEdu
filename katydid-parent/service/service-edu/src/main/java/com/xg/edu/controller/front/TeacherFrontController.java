package com.xg.edu.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.commonutils.Message;
import com.xg.edu.entity.Course;
import com.xg.edu.entity.Teacher;
import com.xg.edu.service.CourseService;
import com.xg.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/edu/frontTeacher")
public class TeacherFrontController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    //前端分页查询
    @GetMapping("/getTeacherList/{page}/{limit}")
    public Message getTeacherFront(@PathVariable("page") long page,
                                   @PathVariable("limit") long limit){
        Page<Teacher> teacherPage = new Page<>();
        Map<String,Object> map = teacherService.frontGetTeachList(teacherPage);

        return Message.successful().add("teachList",map);
    }

    //根据讲师id查询讲师详情和讲师课程
    @GetMapping("/getTeacherInfo/{id}")
    public Message getTeacherInfo(@PathVariable("id") String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher == null){
            return Message.fail();
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);
        List<Course> courseList = courseService.list(queryWrapper);
        return Message.successful().add("teacherInfo",teacher).add("teacherCourse",courseList).add("courseLength",courseList.size());
    }

}
