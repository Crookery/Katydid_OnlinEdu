package com.xg.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xg.commonutils.JwtUtils;
import com.xg.commonutils.MD5;
import com.xg.servicebase.exception.KatyException;
import com.xg.ucenter.entity.Member;
import com.xg.ucenter.entity.vo.LoginVo;
import com.xg.ucenter.entity.vo.RegisterVo;
import com.xg.ucenter.entity.vo.ShowVo;
import com.xg.ucenter.mapper.MemberMapper;
import com.xg.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author katydid
 * @since 2022-04-02
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String email = loginVo.getEmail();
        String password = loginVo.getPassword();

        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        //用户邮箱
        if(StringUtils.isEmpty(email)){
            throw new KatyException(500,"用户邮箱不能为空");
        }
        queryWrapper.eq("email",email);
        //用户密码（加密）
        if(StringUtils.isEmpty(password)){
            throw new KatyException(500,"用户密码不能为空");
        }
        queryWrapper.eq("password", MD5.encrypt(password));
        //是否被禁用
        queryWrapper.ne("is_disabled",1);
        //查询结果
        Member member = getOne(queryWrapper);
        if(member == null){
            throw new KatyException(500,"密码错误");
        }
        return JwtUtils.getJwtToken(member.getId(),member.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) {
        String nickName = registerVo.getNickname();
        String email = registerVo.getEmail();
        String code = registerVo.getCode();
        String password = registerVo.getPassword();
        //判空处理
        if(StringUtils.isEmpty(nickName) || StringUtils.isEmpty(email) || StringUtils.isEmpty(code) || StringUtils.isEmpty(password)){
            throw new KatyException(500,"error");
        }
        //验证code
        String origin = redisTemplate.opsForValue().get(email);
        if(!code.equals(origin)){
            throw new KatyException(500,"验证码错误");
        }
        //验证格式，和是否存在相同的email
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("email",email);
        Integer count = baseMapper.selectCount(memberQueryWrapper);
        if(count > 0){
            throw new KatyException(500,"该邮箱已注册");
        }
        //注册
        Member member = new Member();
        member.setNickname(nickName);
        member.setEmail(email);
        member.setPassword(MD5.encrypt(password));
        member.setAvatar("https://katydid-online.oss-cn-beijing.aliyuncs.com/default.gif");
        save(member);
    }

    @Override
    public ShowVo getLoginInfo(String id) {
        if(StringUtils.isEmpty(id)){
            throw new KatyException(500,"id为空");
        }
        Member member = getById(id);
        ShowVo showVo = new ShowVo();
        assert member!=null;
        BeanUtils.copyProperties(member,showVo);
        return showVo;
    }

    @Override
    public Member getOpenIdMember(String openid) {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("openid",openid);
        return getOne(memberQueryWrapper);
    }

    @Override
    public Integer countRegisters(String day) {
        return baseMapper.countRegisters(day);
    }
}
