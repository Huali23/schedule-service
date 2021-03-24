package com.cizing.service.jobs.upkeep;

import com.cizing.system.common.utils.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liujingeng
 * @description 保养管理定时任务业务类
 * @create 2020/09/10
 */
@Service
public class UpkeepJobService {

    private static final Logger log = LoggerFactory.getLogger(UpkeepJobService.class);

    @Autowired
    UpkeepFeign upkeepFeign;

    public void insertUpkeepTasksByRuntime(){
        ResponseModel<Integer> responseModel = upkeepFeign.insertUpkeepTasksByRuntime();
        if (responseModel.getSuccessful()){
            log.info("upkeep job success, insert task num : " + responseModel.getData());
        } else {
            log.error("upkeep Job error : insert task error :" + responseModel.getMessage());
        }
    }

}