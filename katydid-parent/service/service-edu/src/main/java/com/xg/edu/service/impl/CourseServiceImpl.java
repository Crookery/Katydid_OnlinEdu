package com.xg.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.edu.entity.Chapter;
import com.xg.edu.entity.Course;
import com.xg.edu.entity.CourseDescription;
import com.xg.edu.entity.vo.CourseInfo;
import com.xg.edu.entity.vo.CoursePublishInfoVo;
import com.xg.edu.entity.vo.CourseQuery;
import com.xg.edu.entity.vo.front.FrontCourseDetailVo;
import com.xg.edu.entity.vo.front.FrontCourseSearchVo;
import com.xg.edu.mapper.CourseMapper;
import com.xg.edu.service.ChapterService;
import com.xg.edu.service.CourseDescriptionService;
import com.xg.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.servicebase.exception.KatyException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private CourseDescriptionService descriptionService;

    @Autowired
    private ChapterService chapterService;

    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        //进行条件判断
        if(courseInfo == null || courseInfo.getTitle() == null || courseInfo.getTeacherId() == null || courseInfo.getSubjectId() == null || courseInfo.getSubjectParentId() == null){
            throw new KatyException(500,"添加课程信息为空");
        }
        if(courseInfo.getCover() == null){
            courseInfo.setCover("");
        }
        if(courseInfo.getDescription() == null){
            courseInfo.setDescription("无");
        }
        //1.添加 course信息
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo,course);
        int insert = baseMapper.insert(course); //查询之后，course中的ID就已自动生成（反射）
        if(insert == 0){
            throw new KatyException(500,"添加失败");
        }
        //2.添加 courseDescription
        String c_id = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfo.getDescription());
        courseDescription.setId(c_id);
        descriptionService.save(courseDescription);

        return c_id;
    }

    @Override
    public CourseInfo getCourseInfo(String courseID) {
        if(StringUtils.isEmpty(courseID)){
            throw new KatyException(500,"课程ID不能为空");
        }
        CourseInfo courseInfo = new CourseInfo();
        //查询课程
        Course course = baseMapper.selectById(courseID);
        if(course == null){
            throw new KatyException(500,"无效的ID");
        }
        //查询课程简介
        CourseDescription description = descriptionService.getById(courseID);
        //封装
        BeanUtils.copyProperties(course,courseInfo);
        courseInfo.setDescription(description.getDescription());
        //返回
        return courseInfo;
    }

    @Override
    public boolean updateCourseInfo(CourseInfo courseInfo) {
        if(courseInfo == null){
            throw new KatyException(500,"信息不能为空");
        }
        //更新课程表
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo,course);
        baseMapper.updateById(course);

        //更新简介表
        CourseDescription description =  new CourseDescription();
        description.setId(courseInfo.getId());
        description.setDescription(courseInfo.getDescription());
        descriptionService.updateById(description);

        //若都成功，则返回成功
        return true;
    }

    @Override
    public CoursePublishInfoVo getPublishCourseInfo(String id) {
        CoursePublishInfoVo coursePublishInfoVo = baseMapper.getCoursePublishInfoVo(id);
        if(coursePublishInfoVo == null){
            throw new KatyException(500,"无效id");
        }else{
            return coursePublishInfoVo;
        }
    }

    @Override
    public Page<Course> pageByCondition(long current, long limit, CourseQuery cq) {
        Page<Course> page = new Page<>(current,limit);
        //判断空
        if(cq == null){
            this.page(page,null);
            return page;
        }
        //封装条件
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        String title = cq.getTitle();
        String teacherId = cq.getTeacherId();
        String subjectParentId = cq.getSubjectParentId();
        String subjectId = cq.getSubjectId();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId) ) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.eq("subject_id", subjectId);
        }
        this.page(page,queryWrapper);
        return page;
    }

    @Override
    public boolean delCourseById(String courseId) {
        if(courseId == null){
            throw new KatyException(500,"参数异常，为空");
        }
        QueryWrapper<Chapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<Chapter> list = chapterService.list(wrapper);
        for(Chapter c:list){
            chapterService.delAllBelongToChapter(c.getId());
        }
        //删除简介
        descriptionService.delDescription(courseId);
        //删除本课程
        this.removeById(courseId);

        return true;
    }

    @Override
    public List<Course> getHotCourseData() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");
        return list(queryWrapper);
    }

    @Override
    public Map<String, Object> frontCourseList(Page<Course> coursePage, FrontCourseSearchVo frontCourseSearchVo) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        if(frontCourseSearchVo != null) {
            if (!StringUtils.isEmpty(frontCourseSearchVo.getSubjectParentId())) {
                queryWrapper.eq("subject_parent_id", frontCourseSearchVo.getSubjectParentId());
            }
            if (!StringUtils.isEmpty(frontCourseSearchVo.getSubjectId())) {
                queryWrapper.eq("subject_id", frontCourseSearchVo.getSubjectId());
            }
            if (!StringUtils.isEmpty(frontCourseSearchVo.getBuyCountSort())) {
                queryWrapper.orderByDesc("buy_count");
            }
            if (!StringUtils.isEmpty(frontCourseSearchVo.getGmtCreateSort())) {
                queryWrapper.orderByDesc("gmt_create");
            }
            if (!StringUtils.isEmpty(frontCourseSearchVo.getPriceSort())) {
                queryWrapper.orderByDesc("price");
            }
        }
        baseMapper.selectPage(coursePage, queryWrapper);
        List<Course> records = coursePage.getRecords();
        long current = coursePage.getCurrent();
        long pages = coursePage.getPages();
        long size = coursePage.getSize();
        long total = coursePage.getTotal();
        boolean hasNext = coursePage.hasNext();
        boolean hasPrevious = coursePage.hasPrevious();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public FrontCourseDetailVo getFrontCourseDetail(String id) {
        return baseMapper.getFrontCourseDetail(id);
    }
}
