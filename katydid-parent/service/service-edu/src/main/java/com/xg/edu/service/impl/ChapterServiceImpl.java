package com.xg.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.edu.client.VodClient;
import com.xg.edu.entity.Chapter;
import com.xg.edu.entity.Video;
import com.xg.edu.entity.vo.chapter.ChapterTreeNodeVo;
import com.xg.edu.mapper.ChapterMapper;
import com.xg.edu.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.edu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-03-18
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClient vodClient;


    @Override
    public List<ChapterTreeNodeVo> getTreeChapter(String courseID) {
        //建立root结点
        ChapterTreeNodeVo root = new ChapterTreeNodeVo(null,"root",null,new ArrayList<>());
        //根据课程名查询章节和小节信息
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id",courseID);
        List<Chapter> chapters = baseMapper.selectList(chapterQueryWrapper);

        QueryWrapper<Video> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",courseID);
        List<Video> videos = videoService.list(videoQueryWrapper);

        //包装root结点，只有两层，不用考虑递归
        for(Chapter c:chapters){
            //章节没有videoSourceId
            root.getChildren().add(new ChapterTreeNodeVo(c.getId(),c.getTitle(),null,new ArrayList<>()));
        }
        for(ChapterTreeNodeVo treeNode:root.getChildren()){
            for(Video v:videos) {
                if (v.getChapterId().equals(treeNode.getId())) {
                    treeNode.getChildren().add(new ChapterTreeNodeVo(v.getId(), v.getTitle(), v.getVideoSourceId(),null));
                }
            }
        }
        return root.getChildren();
    }

    //TODO 提高性能：新写一个批量删除的VodClient和vodService中的方法
    @Override
    public boolean delAllBelongToChapter(String chapterID) {
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterID);
        //获取视频id
        List<Video> list = videoService.list(wrapper);

        //删除该章节下的所有小节（包括视频）
        boolean video = videoService.remove(wrapper);
        for(Video v:list){
            String id = v.getVideoSourceId();
            if(id != null && !"".equals(id)){
                vodClient.deleteVideo(id);
            }
        }

        //再删除该章节
        boolean chapter = removeById(chapterID);

        return video && chapter;
    }

}
