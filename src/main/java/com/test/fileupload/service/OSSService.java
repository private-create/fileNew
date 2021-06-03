package com.test.fileupload.service;

import com.test.fileupload.DTO.DriverImageDTO;

import java.util.List;

public interface OSSService {


    public void oSSUpload(DriverImageDTO driverImageDTO);


    public List<String> listFileName(Integer maxKeys);


    public String downFile(String fileName);

}
