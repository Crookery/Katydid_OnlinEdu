package com.xg.servicebase.exception;

import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class KatyException extends RuntimeException{
    private Integer code;
    private String msg;

    public KatyException(){}
    public KatyException(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
