package com.xg.edu.controller;

import com.xg.commonutils.Message;
import com.xg.edu.entity.Subject;
import com.xg.edu.entity.vo.excel.SubjectTreeData;
import com.xg.edu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-03-14
 */

@RestController
@RequestMapping("/edu/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 添加课程分类
     */
    @PostMapping("/addSubject")
    public Message addSubject(MultipartFile file){
        //逻辑链：文件Listener需要操作数据库又不能交给Spring管理 ——》通过有参构造器传入service
        //调用处理文件方法是service做 ——》service方法设置形参接受service，调用方法里再通过有参构造器传入service
        subjectService.addSubject(file,subjectService);
        return Message.successful();
    }

    @GetMapping("/getAllTreeSubject")
    public Message getAllTreeSubject(){
        List<SubjectTreeData> subjectTreeData = subjectService.treeSubject();
        return Message.successful().add("tree",subjectTreeData);
    }

    @GetMapping("/getOneSubject")
    public Message getOneSubject(){
        List<Subject> oneSubject = subjectService.getOneSubject();
        return Message.successful().add("one",oneSubject);
    }

    @GetMapping("/getTwoSubject/{id}")
    public Message getTwoSubject(@PathVariable("id") String oneSubjectID){
        List<Subject> twoSubject = subjectService.getTwoSubject(oneSubjectID);
        return Message.successful().add("two",twoSubject);
    }



}

