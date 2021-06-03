package com.test.fileupload.controller;

import com.test.fileupload.User;
import com.test.fileupload.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
//@Import({User.class})
public class RedisController {

    @Autowired
    private RedisService redisService;


    @Resource(type = ApplicationContext.class)
    private ApplicationContext app;


    @ResponseBody
    @RequestMapping("/redis")
    public String setKey(){
        System.out.println("建立连接");
        redisService.setOnlyStrEx("springRedis","zhangfei1",3000);
        System.out.println(((User)app.getBean("user")).getUserName());
        return redisService.getOnlyString("springRedis").toString();
    }


}
