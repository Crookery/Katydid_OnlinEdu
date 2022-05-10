package com.xg.edu.service.impl;

import com.xg.edu.entity.Comment;
import com.xg.edu.mapper.CommentMapper;
import com.xg.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-04-04
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
