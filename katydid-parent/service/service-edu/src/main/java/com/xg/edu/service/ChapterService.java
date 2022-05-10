package com.xg.edu.service;

import com.xg.edu.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.edu.entity.vo.chapter.ChapterTreeNodeVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
public interface ChapterService extends IService<Chapter> {

    /**
     * 根据课程ID，列出章节大纲（章节[小节…]）
     */
    List<ChapterTreeNodeVo> getTreeChapter(String courseID);

    /**
     * 根据章节ID，删除其所有小节和该章节
     */
    boolean delAllBelongToChapter(String chapterID);

}
