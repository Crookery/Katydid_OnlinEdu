package com.xg.ucenter.controller;

import com.google.gson.Gson;
import com.xg.commonutils.JwtUtils;
import com.xg.servicebase.exception.KatyException;
import com.xg.ucenter.entity.Member;
import com.xg.ucenter.service.MemberService;
import com.xg.ucenter.utils.ConstVXConfig;
import com.xg.ucenter.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;


@Controller
@RequestMapping("/api/ucenter/wx")
public class VXController {
    @Autowired
    private MemberService memberService;

    //1.生成二维码
    @GetMapping("login")
    public String getVXCode(){
        //请求vx地址：固定地址，后面拼接参数（%s表示占位符）
        String baseUrl ="https://open.weixin.qq.com/connect/qrconnect" +
                        "?appid=%s" +
                        "&redirect_uri=%s" +
                        "&response_type=code" +
                        "&scope=snsapi_login" +
                        "&state=%s" +
                        "#wechat_redirect";
        //设置 %s 占位符值
        String url = String.format(
                        baseUrl,
                        ConstVXConfig.APP_ID,
                        ConstVXConfig.REDIRECT_URL,
                        "katydid"
                      );
        //编码
        String redirectUrl = ConstVXConfig.REDIRECT_URL;
        try {
            URLEncoder.encode(redirectUrl,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //重定向到请求地址去
        return "redirect:"+url;
    }

    //2.获取扫码人的信息
    @GetMapping("callback")
    public String callback(String code,String state){
            //① 获取code
        try {
            //② 拿着code，请求微信固定地址，获取：access_token 和 openid
            String baseAccessTokenUrl =
                    "https://api.weixin.qq.com/sns/oauth2/access_token" +
                            "?appid=%s" +
                            "&secret=%s" +
                            "&code=%s" +
                            "&grant_type=authorization_code";
            String accessTokenUrl = String.format(baseAccessTokenUrl,ConstVXConfig.APP_ID,ConstVXConfig.APP_SECRET,code);

            //拼接好请求地址，返回两个值 access_id 和 openid
            //使用httpclient发送请求（不需要浏览器，也可以发送请求，得到数据）
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            //③ 从accessTokenInfo获取关键值
            Gson gson = new Gson();
            HashMap hashMap = gson.fromJson(accessTokenInfo, HashMap.class);
            String accessToken = (String)hashMap.get("access_token");
            String openid = (String) hashMap.get("openid");

            //④ 获取用户信息
            Member member = memberService.getOpenIdMember(openid);
            if(member == null){    //新注册
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);
                String resultUserInfo = HttpClientUtils.get(userInfoUrl);
                HashMap infoMap = gson.fromJson(resultUserInfo, HashMap.class);
                String nickName = (String) infoMap.get("nickname");
                String headUrl = (String) infoMap.get("headimgurl");

                member = new Member();
                member.setOpenid(openid);
                member.setNickname(nickName);
                member.setAvatar(headUrl);
                memberService.save(member);
            }
            //⑤ 因为cookie不能跨域，所以使用token追加到url中
            String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            return "redirect:http://localhost:3000?token="+token;
        } catch (Exception e) {
            throw new KatyException(500,"登录失败");
        }
    }

}
