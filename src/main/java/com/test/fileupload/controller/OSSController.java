package com.test.fileupload.controller;


import com.test.fileupload.DTO.DriverImageDTO;
import com.test.fileupload.service.OSSService;
import com.test.fileupload.util.ResponseEntity;
import com.test.fileupload.util.ResponseResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

@Controller
public class OSSController {


    @Autowired
    private OSSService ossService;

    @ResponseBody
    @PostMapping("/safe/driverDetect")
    public ResponseEntity upload(@RequestBody DriverImageDTO driverImageDTO){
        System.out.println("图片上传***********************");
        if(driverImageDTO.getType().equals("1")){
            ossService.oSSUpload(driverImageDTO);
        }
        return ResponseResult.success();
    }

    @ResponseBody
    @GetMapping("/get/list")
    public ResponseEntity getList(@RequestParam("maxKeys") Integer maxKeys){
        List<String> strings = ossService.listFileName(maxKeys);
        return ResponseResult.success(strings);
    }

    @ResponseBody
    @GetMapping("/get/file")
    public ResponseEntity getFile(@RequestParam("name")String name) throws Exception{
        String s = ossService.downFile(name);
        FileOutputStream outputStream = new FileOutputStream("D:"+File.separator+"3.jpg");
        outputStream.write(Base64.decodeBase64(s));
        return ResponseResult.success(s);
    }
}
