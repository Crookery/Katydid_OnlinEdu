package com.xg.ucenter.service;

import com.xg.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xg.ucenter.entity.vo.LoginVo;
import com.xg.ucenter.entity.vo.RegisterVo;
import com.xg.ucenter.entity.vo.ShowVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author katydid
 * @since 2022-04-02
 */
public interface MemberService extends IService<Member> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);

    ShowVo getLoginInfo(String id);

    Member getOpenIdMember(String openid);

    Integer countRegisters(String day);

}
