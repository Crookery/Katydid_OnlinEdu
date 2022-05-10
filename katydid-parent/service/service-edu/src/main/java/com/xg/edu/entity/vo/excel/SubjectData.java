package com.xg.edu.entity.vo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

@Data   //get set toString equals
@NoArgsConstructor
@AllArgsConstructor
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubject;

    @ExcelProperty(index = 1)
    private String twoSubject;
}
