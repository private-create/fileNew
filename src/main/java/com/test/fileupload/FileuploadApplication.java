package com.test.fileupload;

import com.test.fileupload.service.WebSocketServiceTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan(value = "com.test.fileupload.mapper")
@SpringBootApplication
public class FileuploadApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(FileuploadApplication.class, args);

       WebSocketServiceTest.setApplicationContext(run);

    }

}
