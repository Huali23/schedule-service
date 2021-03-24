package com.cizing.service.jobs.upkeep;

import com.cizing.system.common.utils.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "office-service")
public interface UpkeepFeign {

    @PostMapping("/upkeep/tasks/enable")
    ResponseModel insertUpkeepTasksByRuntime();

}
