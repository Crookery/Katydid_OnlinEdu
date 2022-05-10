package com.xg.servicebase.handler;

import com.xg.commonutils.Message;
import com.xg.servicebase.exception.KatyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  自定义异常处理：处理粒度采取就近原则（尽量精准）
 */
@ControllerAdvice   //加强controller，一般用于配置异常处理
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody   //返回数据
    public Message error(Exception e){
        e.printStackTrace();
        return Message.fail().add("fail","调用了全局异常处理");
    }

    @ExceptionHandler(KatyException.class)
    @ResponseBody
    public Message error(KatyException e){
        e.printStackTrace();
        return Message.fail().add("fail_msg",e.getMsg()).add("fail_code",e.getCode());
    }

}
