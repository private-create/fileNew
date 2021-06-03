package com.test.fileupload.xxljob;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@JobHandler("autoHello")
@Component
@Slf4j
public class AutoHello extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        log.info("我的第一个xxl-job任务执行.{}","hello world");
        return null;
    }
}
