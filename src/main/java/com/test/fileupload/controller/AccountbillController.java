package com.test.fileupload.controller;

import com.test.fileupload.entity.Accountbill;
import com.test.fileupload.service.AccountbillService;
import com.test.fileupload.util.ResponseEntity;
import com.test.fileupload.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AccountbillController {

    @Autowired
    private AccountbillService accountbillService;

    @ResponseBody
    @GetMapping("/get/accountbill")
    public ResponseEntity<List<Accountbill>> queryAccountbillAll(){
      return   ResponseResult.success(accountbillService.queryAccountbillAll());
    }


    @ResponseBody
    @GetMapping("/acc/sendEmail")
    public ResponseEntity<Boolean> sendEmail(){
        return   ResponseResult.success(accountbillService.sendEmail());
    }
}
