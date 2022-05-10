package com.xg.edu.service;

import com.xg.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.edu.entity.vo.excel.SubjectTreeData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-03-14
 */
public interface SubjectService extends IService<Subject> {

    /**
     * 通过excel文件，批量添加
     */
    void addSubject(MultipartFile file,SubjectService subjectService);

    /**
     * 判断是否存在一级/二级分类
     * @param name 分类名称
     * @param pid  0表示一级，非0表示二级
     * @return null=不存在
     */
    Subject existSubject(String name,String pid);

    /**
     * 返回树形的一级/二级课程分类
     */
    List<SubjectTreeData> treeSubject();

    /**
     * 返回所有一级分类
     */
    List<Subject> getOneSubject();

    /**
     * 返回一级分类下的所有二级分类
     */
    List<Subject> getTwoSubject(String oneSubjectID);
}
