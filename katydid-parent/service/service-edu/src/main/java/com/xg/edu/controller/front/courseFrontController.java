package com.xg.edu.controller.front;


import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.commonutils.JwtUtils;
import com.xg.commonutils.Message;
import com.xg.edu.client.OrderClient;
import com.xg.edu.entity.Course;
import com.xg.edu.entity.vo.chapter.ChapterTreeNodeVo;
import com.xg.edu.entity.vo.front.FrontCourseDetailVo;
import com.xg.edu.entity.vo.front.FrontCourseSearchVo;
import com.xg.edu.service.ChapterService;
import com.xg.edu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/edu/frontCourse")
public class courseFrontController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    //分页-条件查询课程
    @PostMapping("/getFrontCourseList/{page}/{limit}")
    public Message getFrontCourseList(@PathVariable("page") long page,
                                      @PathVariable("limit") long limit,
                                      @RequestBody(required = false) FrontCourseSearchVo frontCourseSearchVo){

        Page<Course> coursePage = new Page<>(page,limit);
        Map<String,Object> map = courseService.frontCourseList(coursePage, frontCourseSearchVo);

        return Message.successful().add("courseMap",map);
    }

    //课程id=>课程详情
    @GetMapping("getCourseDetail/{id}")
    public Message getCourseDetail(@PathVariable("id") String id, HttpServletRequest request){
        //课程基本信息
        FrontCourseDetailVo frontCourseDetail = courseService.getFrontCourseDetail(id);

        //获取{章节[小节]}
        List<ChapterTreeNodeVo> treeChapter = chapterService.getTreeChapter(id);

        //根据课程id和用户id查询当前课程是否已经支付过
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isEmpty(memberIdByJwtToken)){
            return Message.fail().add("msg","用户未登录");
        }
        boolean isBuyCourse = orderClient.isBuyCourse(id, memberIdByJwtToken);

        return Message.successful().add("detail",frontCourseDetail).add("chapter",treeChapter).add("isBuy",isBuyCourse);
    }

    @PostMapping("getCourseInfoToCourse/{id}")
    public Message getCourseInfoToOrder(@PathVariable("id") String id){
        FrontCourseDetailVo courseInfo = courseService.getFrontCourseDetail(id);
        if(courseInfo != null){
            HashMap<String,Object> map = new HashMap<>();
            map.put("id",courseInfo.getId());
            map.put("title",courseInfo.getTitle());
            map.put("cover",courseInfo.getCover());
            map.put("teacherName",courseInfo.getTeacherName());
            map.put("fee",courseInfo.getPrice()+"");    //变为字符串
            map.put("teacherId",courseInfo.getTeacherId());
            return Message.successful().replace(map);
        }else{
            return Message.fail();
        }
    }


}
