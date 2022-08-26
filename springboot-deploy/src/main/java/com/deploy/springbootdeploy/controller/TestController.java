package com.deploy.springbootdeploy.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiaqi.zhang
 * @version 1.0
 * @date 2022/8/2 20:13
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "...Hello World...";
    }
}
