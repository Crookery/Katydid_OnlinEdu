package com.xg.order.client;

import com.xg.commonutils.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("service-edu")
public interface EduClient {

    @PostMapping("/edu/frontCourse/getCourseInfoToCourse/{id}")
    Message getCourseInfoToOrder(@PathVariable("id") String id);

}
