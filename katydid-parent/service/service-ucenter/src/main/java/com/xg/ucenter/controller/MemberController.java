package com.xg.ucenter.controller;


import com.xg.commonutils.JwtUtils;
import com.xg.commonutils.Message;
import com.xg.ucenter.entity.Member;
import com.xg.ucenter.entity.vo.LoginVo;
import com.xg.ucenter.entity.vo.RegisterVo;
import com.xg.ucenter.entity.vo.ShowVo;
import com.xg.ucenter.service.MemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author katydid
 * @since 2022-04-02
 */

@RestController
@RequestMapping("/ucenter/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    //要求：密码传输不加密
    @ApiOperation("登录")
    @PostMapping("/login")
    public Message login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        if("".equals(token)){
            return Message.fail();
        }
        return Message.successful().add("token",token);
    }

    //要求：前端传过来的数据加密
    @ApiOperation("注册")
    @PostMapping("/register")
    public Message register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Message.successful();
    }

    //根据token获取用户信息
    @GetMapping("/getUserInfo")
    public Message getLoginInfo(HttpServletRequest httpServletRequest){
        String id = JwtUtils.getMemberIdByJwtToken(httpServletRequest);
        if(StringUtils.isEmpty(id)){
            return Message.fail();
        }
        ShowVo showVo = memberService.getLoginInfo(id);
        return Message.successful().add("loginInfo",showVo);
    }

    //根据用户id获取用户信息
    @PostMapping("getUsrInfoToOrder/{id}")
    public Message getUserInfoToOrder(@PathVariable("id") String id){
        Member member = memberService.getById(id);
        if(member != null){
            HashMap<String,Object> map = new HashMap<>();
            map.put("memberId",member.getId());
            map.put("nickName",member.getNickname());
            map.put("email",member.getEmail());
            return Message.successful().replace(map);
        }else{
            return Message.fail();
        }
    }

    //TODO service的BaseMapper出bug了?
    //查询某天的注册人数
    @GetMapping("countRegister/{day}")
    public Message countRegister(@PathVariable("day") String day){
        Integer count = memberService.countRegisters(day);
        return Message.successful().add("count",count);
    }

}

