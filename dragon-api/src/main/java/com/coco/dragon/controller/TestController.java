package com.coco.dragon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liaoshen
 */
@RestController
@RequestMapping("/business")
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "hello world";
    }
}
