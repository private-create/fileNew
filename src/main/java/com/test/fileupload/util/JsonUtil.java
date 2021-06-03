package com.test.fileupload.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static <T> T JsonStringToObject(String source,Class<T> clazz){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           return objectMapper.readValue(source, clazz);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
