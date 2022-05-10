package com.xg.edu.controller;


import com.xg.commonutils.Message;
import com.xg.edu.entity.Chapter;
import com.xg.edu.entity.vo.chapter.ChapterTreeNodeVo;
import com.xg.edu.service.ChapterService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */

@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/getChapterVideo/{courseID}")
    public Message getChapterVideo(@PathVariable("courseID") String courseID){
        List<ChapterTreeNodeVo> treeChapter = chapterService.getTreeChapter(courseID);
        return Message.successful().add("treeChapter",treeChapter);
    }

    @ApiOperation("查询章节")
    @GetMapping("/getChapter/{id}")
    public Message getChapter(@PathVariable("id") String chapterId){
        Chapter chapter = chapterService.getById(chapterId);
        return Message.successful().add("chapter",chapter);
    }

    @ApiOperation("添加章节")
    @PostMapping("/addChapter")
    public Message addChapter(@RequestBody Chapter chapter){
        return chapterService.save(chapter)?Message.successful():Message.fail();
    }

    @ApiOperation("修改章节")
    @PostMapping("/updateChapter/{chapterID}")
    public Message updateChapter(@PathVariable String chapterID,@RequestBody Chapter chapter){
        chapter.setId(chapterID);
        return chapterService.updateById(chapter)?Message.successful():Message.fail();
    }

    @ApiOperation("删除章节及该章节下的所有小节")
    @DeleteMapping("/deleteChapter/{chapterID}")
    public Message delChapter(@PathVariable String chapterID){
        return chapterService.delAllBelongToChapter(chapterID)?Message.successful():Message.fail();
    }






}

