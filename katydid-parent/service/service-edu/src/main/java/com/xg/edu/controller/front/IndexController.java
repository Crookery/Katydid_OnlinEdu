package com.xg.edu.controller.front;

import com.xg.commonutils.Message;
import com.xg.edu.entity.Course;
import com.xg.edu.entity.Teacher;
import com.xg.edu.service.CourseService;
import com.xg.edu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/edu/index")
public class IndexController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/hotData")
    public Message getHotData(){
        List<Teacher> teacherList = teacherService.getHotTeacherData();
        List<Course> courseList = courseService.getHotCourseData();
        return Message.successful().add("hotTeacher",teacherList).add("hotCourse",courseList);
    }
}
