package com.xg.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.edu.entity.Subject;
import com.xg.edu.entity.vo.excel.SubjectData;
import com.xg.edu.entity.vo.excel.SubjectTreeData;
import com.xg.edu.listener.SubjectListener;
import com.xg.edu.mapper.SubjectMapper;
import com.xg.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.servicebase.exception.KatyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-03-14
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Override
    public void addSubject(MultipartFile file, SubjectService subjectService) {
        try{
            if(file == null){
                throw new KatyException(500,"上传文件为空");
            }
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectListener(subjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<SubjectTreeData> treeSubject() {
        //1.构造根结点
        SubjectTreeData root = new SubjectTreeData("0","root",new ArrayList<>());
        //2.查询一级/二级数据
        QueryWrapper<Subject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id","0");
        List<Subject> one = list(oneWrapper);
        QueryWrapper<Subject> twoWrapper = new QueryWrapper<>();
        oneWrapper.ne("parent_id","0");
        List<Subject> two = list(twoWrapper);
        //3.构建树——先构建一层结点
        for(Subject o:one){
            root.getChildren().add(new SubjectTreeData(o.getId(),o.getTitle(),new LinkedList<>()));
        }
        //因为只有二层结点，所以可以简化问题
        int[] leap = new int[two.size()];   //构建标记表
        for(SubjectTreeData s:root.getChildren()){
            for(int i=0;i<two.size();i++){
                Subject t = two.get(i);
                if(leap[i]!=1 && s.getId().equals(t.getParentId())){
                    s.getChildren().add(new SubjectTreeData(t.getId(),t.getTitle(),null));
                    leap[i]=1;
                }
            }
            if(s.getChildren().size() == 0){
                s.setChildren(null);
            }
        }
        return root.getChildren();
    }

    @Override
    public Subject existSubject(String name, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return this.getOne(wrapper);
    }

    @Override
    public List<Subject> getOneSubject() {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",0);
        return this.list(wrapper);
    }

    @Override
    public List<Subject> getTwoSubject(String oneSubjectID) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",oneSubjectID);
        return this.list(wrapper);
    }
}
