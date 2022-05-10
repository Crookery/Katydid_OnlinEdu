package com.xg.ucenter.mapper;

import com.xg.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author katydid
 * @since 2022-04-02
 */
public interface MemberMapper extends BaseMapper<Member> {

    Integer countRegisters(String day);
}
