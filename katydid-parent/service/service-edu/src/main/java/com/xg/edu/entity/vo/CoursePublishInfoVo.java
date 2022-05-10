package com.xg.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 发布课程——最终发布页信息
 */
@Data
public class CoursePublishInfoVo implements Serializable {
    private static final long serialVersionUID = 5L;

    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;           //只用于显示
}
