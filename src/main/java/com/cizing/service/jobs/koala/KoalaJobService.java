package com.cizing.service.jobs.koala;

import com.cizing.service.jobs.upkeep.UpkeepFeign;
import com.cizing.service.jobs.upkeep.UpkeepJobService;
import com.cizing.system.common.utils.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liujingeng
 * @description 旷世人脸识别同步历史记录
 * @create 2020/11/10
 */
@Service
public class KoalaJobService {

    private static final Logger log = LoggerFactory.getLogger(UpkeepJobService.class);

    @Autowired
    KoalaFeign koalaFeign;

    public void insertUpkeepTasksByRuntime(){
        ResponseModel<Integer> responseModel = koalaFeign.saveEvents();
        if (responseModel.getSuccessful()){
            log.info("koalaEvent job success" + new Date().toString());
        } else {
            log.error("koalaEvent job error " + new Date().toString());
        }
    }

}