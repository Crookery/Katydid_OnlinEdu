package com.xg.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.edu.entity.vo.TeacherQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-02-24
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 分页查询讲师信息
     */
    Page<Teacher> pageQuery(long current,long limit,TeacherQuery tq);

    /**
     * 查询热门讲师数据
     * @return 按id降序排列的前4个
     */
    List<Teacher> getHotTeacherData();

    Map<String, Object> frontGetTeachList(Page<Teacher> teacherPage);
}
