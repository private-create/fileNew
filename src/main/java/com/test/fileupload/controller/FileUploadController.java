package com.test.fileupload.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;

@Controller
@PropertySource({"classpath:static/properConfig.properties"})
@CrossOrigin
public class FileUploadController {

    @Value("${file_save_path}")
    private String filePath;

    @ResponseBody
    @RequestMapping("/upload/file")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest){

        Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();

        String fileName = file.getOriginalFilename();

        File staticFile = new File(filePath + File.separator + fileName);

        try {
            file.transferTo(staticFile);
        }catch (Exception e){
            e.printStackTrace();

        }
        return "成功";
    }

    @ResponseBody
    @RequestMapping("/upload/files")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files){

        for (MultipartFile multipartFile : files){

            String fileName = multipartFile.getOriginalFilename();

            try {
                File file = new File(filePath + File.separator + fileName);

                multipartFile.transferTo(file);
            }catch (Exception e){
                e.printStackTrace();

            }
        }
        return "成功";
    }

    @ResponseBody
    @RequestMapping("/down")
    public String getFile(HttpServletRequest req, HttpServletResponse resp){
        File file = new File("D:"+File.separator+"web"+File.separator+"test.log");
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
//            resp.reset();
            resp.setContentType("application/octet-stream");
            String filename = file.getName();
            resp.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            byte[] by = new byte[1024];
            int temp = -1;
            while ((temp=fileInputStream.read(by))!=-1){
                resp.getOutputStream().write(by);
            }
            fileInputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "ok";
    }
}
