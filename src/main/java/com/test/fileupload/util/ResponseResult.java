package com.test.fileupload.util;

public class ResponseResult {


    public static <T> ResponseEntity success(T t){
        ResponseEntity<T> responseEntity = new ResponseEntity();
        responseEntity.setCode(200);
        responseEntity.setMsg("成功");
        responseEntity.setData(t);
        return responseEntity;
    }


    public static  ResponseEntity success(){
        ResponseEntity responseEntity = new ResponseEntity();
        responseEntity.setCode(200);
        responseEntity.setMsg("成功");
        return responseEntity;
    }
}
