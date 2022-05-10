package com.xg.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.edu.entity.Teacher;
import com.xg.edu.entity.vo.TeacherQuery;
import com.xg.edu.mapper.TeacherMapper;
import com.xg.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * front就是前端的方法
 *
 * @author katydid
 * @since 2022-02-24
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Override
    public Page<Teacher> pageQuery(long current, long limit, TeacherQuery tq) {
        Page<Teacher> page = new Page<>(current,limit);
        //1.无条件的分页查询
        if(tq == null){
            super.page(page,null);
            return page;
        }
        //2.带条件的分页查询

        //2.1.创建用于拼接条件的对象
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        //2.2.拼接条件
        String begin = tq.getBegin();
        String end = tq.getEnd();
        Integer level = tq.getLevel();
        String name = tq.getName();
        if(!StringUtils.isEmpty(name)){
            //字段+值
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.ge("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.gt("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        //2.3.按创建时间降序（从晚到早）
        wrapper.orderByDesc("gmt_create");
        //2.4.将结果封装在page对象内
        page(page,wrapper);
        //2.5.返回查询结果
        return page;
    }

    @Override
    public List<Teacher> getHotTeacherData() {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        queryWrapper.last("limit 4");
        return list(queryWrapper);
    }


    @Override
    public Map<String, Object> frontGetTeachList(Page<Teacher> teacherPage) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        baseMapper.selectPage(teacherPage, queryWrapper);
        List<Teacher> records = teacherPage.getRecords();
        long current = teacherPage.getCurrent();
        long pages = teacherPage.getPages();
        long size = teacherPage.getSize();
        long total = teacherPage.getTotal();
        boolean hasNext = teacherPage.hasNext();
        boolean hasPrevious = teacherPage.hasPrevious();
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
}
