package com.cizing.service.jobs.koala;

import com.cizing.system.common.utils.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "vibe-service")
public interface KoalaFeign {

    @PostMapping("/koala/events")
    ResponseModel saveEvents();

}
