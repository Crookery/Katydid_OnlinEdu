package com.xg.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.xg.edu.entity.Subject;
import com.xg.edu.entity.vo.excel.SubjectData;
import com.xg.edu.service.SubjectService;
import com.xg.servicebase.exception.KatyException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 不能交给Spring进行管理，不能注入其他对象，需要自己new
 * 解决方案：使用有参注入service
 */
@AllArgsConstructor
@NoArgsConstructor
public class SubjectListener extends AnalysisEventListener<SubjectData> {

    private SubjectService subjectService;

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        //1.excel文件进行判断，每次读取是一行的数据(subjectData)
        if(subjectData == null){
            throw new KatyException(500,"文件数据为空");
        }
        //文件中数据：一级/二级
        String oneTitle = subjectData.getOneSubject();
        String twoTitle = subjectData.getTwoSubject();

        //2.判断一级目录是否存在
        Subject existOneSubject = this.subjectService.existSubject(oneTitle, "0");
        if(existOneSubject == null){
            //不存在，直接添加
            Subject one = new Subject();
            one.setTitle(oneTitle);
            one.setParentId("0");
            this.subjectService.save(one);
            //存在，不做处理
        }

        //3.判断二级目录是否存在，因为一级目录开始查的时候不存在，第一次创建后存在，所以需再查一次数据库
        existOneSubject = this.subjectService.existSubject(oneTitle, "0");
        Subject existTwoSubject = this.subjectService.existSubject(twoTitle, existOneSubject.getId());
        if(existTwoSubject == null){
            //二级目录不存在，直接添加
            Subject two = new Subject();
            two.setTitle(twoTitle);
            two.setParentId(existOneSubject.getId());
            this.subjectService.save(two);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

}
