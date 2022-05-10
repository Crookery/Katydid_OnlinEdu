package com.xg.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.xg.commonutils.MyStringUtils;
import com.xg.oss.service.OssService;
import com.xg.oss.utils.OssConfigUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvatar(MultipartFile file) {
        // Endpoint
        String endpoint = OssConfigUtils.END_POINT;
        // 阿里云账号Access
        String accessKeyId = OssConfigUtils.KEY_ID;
        String accessKeySecret = OssConfigUtils.KEY_SECRET;
        // 填写Bucket名称
        String bucketName = OssConfigUtils.BUCKET_NAME;

        OSS ossClient = null;
        try {
            //1.创建OSSClient实例
            ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
            //2.获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            //3.获取文件名称（根据时间拼接文件夹）
            UUID uuid = UUID.randomUUID();
            String fileName = MyStringUtils.getDateString()+uuid+file.getOriginalFilename();
            if(!MyStringUtils.isPhotos(fileName)){
                return null;
            }
            //4.创建PutObject请求
            ossClient.putObject(bucketName, fileName, inputStream);

            return "https://"+bucketName+"."+endpoint+"/"+fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //5.关闭OSSClient
            if (ossClient != null){
                ossClient.shutdown();
            }
        }
        return null;
    }
}
