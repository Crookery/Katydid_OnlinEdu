package com.xg.commonutils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * String工具：用于处理文件名
 */
public class MyStringUtils {
    /**
     * 获取文件名后缀
     * @return 返回文件名最后一个.后面的内容
     */
    private static synchronized String getFileNameSuffix(String fileName){
        if(!fileName.contains(".")){
            return null;
        }
        char[] chars = fileName.toCharArray();
        int n = chars.length;
        int point = -1;
        for(int i=0;i<n;i++){
            if(chars[i] == '.'){
                point = i;
            }
        }
        if(point == n-1 || point == -1){
            return null;
        }else{
            return new String(chars,point+1,n-point-1);
        }
    }

    /**
     * 判断是否是图片文件名后缀
     * @return true：允许的图片后缀：jpg、png、gif、ico
     */
    public synchronized static boolean isPhotos(String filename){
        String suffix = MyStringUtils.getFileNameSuffix(filename);
        if(suffix != null){
            String s = suffix.toLowerCase();
            return "jpg".equals(s) || "png".equals(s) || "gif".equals(s) || "ico".equals(s);
        }
        return false;
    }

    public synchronized static String getDateString(){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        return sf.format(new Date())+"/";
    }

    /**
     * 获取6位随机验证码
     */
    public synchronized static String getSixCode(){
        Random random = new Random();
        int i = random.nextInt(30)+1;
        return UUID.randomUUID().toString().substring(i,i+6);
    }


}
