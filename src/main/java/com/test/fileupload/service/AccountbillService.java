package com.test.fileupload.service;

import com.test.fileupload.entity.Accountbill;

import java.util.List;

public interface AccountbillService {

    public List<Accountbill> queryAccountbillAll();

    public boolean sendEmail();
}
