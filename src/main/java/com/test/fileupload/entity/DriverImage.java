package com.test.fileupload.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DriverImage {

    private String name;

    private String no;

    private String Base64Image;

    private String getBase64Imb;

    private String stationId;

    private String identityId;
}
