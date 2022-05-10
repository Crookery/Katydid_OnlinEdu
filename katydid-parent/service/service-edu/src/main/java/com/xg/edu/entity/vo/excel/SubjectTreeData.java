package com.xg.edu.entity.vo.excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 节点，最终用树型表示，返回 root.children
 */
@Data
@AllArgsConstructor
public class SubjectTreeData {

    @ApiModelProperty("章节ID")
    private String id;
    private String label;

    @ApiModelProperty("代表video结点，其中的id是video的")
    private List<SubjectTreeData> children;
}
