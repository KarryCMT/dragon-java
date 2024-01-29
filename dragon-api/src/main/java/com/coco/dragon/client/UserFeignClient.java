package com.coco.dragon.client;

import com.coco.rabbit.common.resp.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liaoshen
 */
@Component
@FeignClient(name = "system-service")
public interface UserFeignClient {

    /**
     * userController中的hello
     * @param name
     * @return
     */
    @GetMapping("hello")
    Result hello(String name);
}
