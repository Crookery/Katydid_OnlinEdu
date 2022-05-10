package com.xg.edu.controller;

import com.xg.commonutils.Message;
import org.springframework.web.bind.annotation.*;

/**
 * 暂时登录的处理
 */

@RestController
@RequestMapping("/edu/user")
public class LoginController {

    @PostMapping("login")
    public Message login(){
        return Message.successful().add("token","admin");
    }

    @GetMapping("info")
    public Message info(){
        return Message.successful().add("roles","[admin]").add("name","admin").add("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
