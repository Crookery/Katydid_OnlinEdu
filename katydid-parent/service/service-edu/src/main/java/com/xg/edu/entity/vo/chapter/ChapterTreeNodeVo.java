package com.xg.edu.entity.vo.chapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterTreeNodeVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String videoSourceId;
    private List<ChapterTreeNodeVo> children;
}
