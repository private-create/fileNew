package com.test.fileupload.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.*;
import com.test.fileupload.DTO.DriverImageDTO;
import com.test.fileupload.config.OSSConfig;
import com.test.fileupload.service.OSSService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class OSSServiceImpl implements OSSService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public void oSSUpload(DriverImageDTO driverImageDTO) {

        byte[] driverImage = Base64.decodeBase64(driverImageDTO.getDriverImage());

        byte[] driverBmp = Base64.decodeBase64(driverImageDTO.getDriverBmp());

        String fileNamePrefix ="193/driver/"+driverImageDTO.getStationId()+ File.separator+ driverImageDTO.getIdentityId()+File.separator;

        PutObjectRequest putObjectRequestDriverImage = new PutObjectRequest(ossConfig.getBucketName(), fileNamePrefix+"image.jpg", new ByteArrayInputStream(driverImage));

        PutObjectRequest putObjectRequestDriverBmp = new PutObjectRequest(ossConfig.getBucketName(), fileNamePrefix+"bmp.jpg", new ByteArrayInputStream(driverBmp));

        OSS oss = ossConfig.getOss();

        oss.putObject(putObjectRequestDriverImage);

        oss.putObject(putObjectRequestDriverBmp);

        oss.shutdown();

    }

    @Override
    public List<String> listFileName(Integer maxKeys) {
        OSS ossClient =null;
        List<String> files = new ArrayList<>();
        try {

            ossClient = ossConfig.getOss();

           // 列举文件。如果不设置KeyPrefix，则列举存储空间下的所有文件。如果设置KeyPrefix，则列举包含指定前缀的文件。
           ObjectListing objectListing = ossClient.listObjects(new ListObjectsRequest(ossConfig.getBucketName()).withPrefix("193/driver").withMaxKeys(maxKeys));
           List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
           for (OSSObjectSummary s : sums) {
               System.out.println("\t" + s.getKey());
               files.add(s.getType());
           }
       }finally {
           if(ossClient != null){
               ossClient.shutdown();
           }
       }
        return files;
    }

    @Override
    public String downFile(String fileName) {
        OSS ossClient =null;
        String result = null;
        try {
            // 创建OSSClient实例。
            ossClient = ossConfig.getOss();


            // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
            OSSObject ossObject = ossClient.getObject(ossConfig.getBucketName(), fileName);
            result = Base64.encodeBase64String(IOUtils.readStreamAsByteArray(ossObject.getObjectContent()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
