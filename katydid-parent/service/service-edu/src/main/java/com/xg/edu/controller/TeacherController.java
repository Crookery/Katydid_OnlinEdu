package com.xg.edu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.commonutils.Message;
import com.xg.edu.entity.Teacher;
import com.xg.edu.entity.vo.TeacherQuery;
import com.xg.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-02-24
 */

@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询所有的讲师信息")
    @GetMapping("/list")
    public Message searchAllTeacher(){
        List<Teacher> list = teacherService.list(null);
        return Message.successful().add("list",list);
    }

    @ApiOperation("分页查询讲师信息")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public Message pageTeacher(@PathVariable("current") long current,
                               @PathVariable("limit") long limit){
        Page<Teacher> page = teacherService.pageQuery(current,limit,null);
        return Message.successful().add("page",page);
    }

    @ApiOperation("根据id查询讲师信息")
    @GetMapping("/getTeacherById/{id}")
    public Message getTeacher(@PathVariable("id") String id){
        Teacher t = teacherService.getById(id);
        return Message.successful().add("teacher",t);
    }

    /**
     *  RequestBody使用json传数据，把json数据封装到对应的对象中，必须是post提交
     */
    @ApiOperation("根据条件分页查询")
    @PostMapping("/pageTeacherByCondition/{current}/{limit}")
    public Message pageTeacherByCondition(@PathVariable("current") long current,
                                          @PathVariable("limit") long limit,
                                          @RequestBody(required = false) TeacherQuery tq){
        Page<Teacher> page = teacherService.pageQuery(current,limit,tq);
        return Message.successful().add("page",page);
    }

    @ApiOperation("修改讲师")
    @PostMapping("/updateTeacher")
    public Message updateTeacher(@RequestBody Teacher teacher){
        return teacherService.updateById(teacher) ? Message.successful() : Message.fail();
    }

    @ApiOperation("根据条件，添加讲师，会自动填充")
    @PostMapping("/addTeacher")
    public Message addTeacher(@RequestBody Teacher teacher){
        return teacherService.save(teacher) ? Message.successful() : Message.fail();
    }

    @ApiOperation("删除讲师")
    @DeleteMapping("{id}")
    public Message removeTeacher(@PathVariable("id")String id){
        return teacherService.removeById(id)?Message.successful():Message.fail();
    }

}

