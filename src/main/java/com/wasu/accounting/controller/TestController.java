package com.wasu.accounting.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangzh on 2017/2/23.
 */
@RestController
public class TestController {

    private static final Logger log = LogManager.getLogger(TestController.class);

    @RequestMapping("/test")
    public String helloWord(){
        log.info("log success");
        return "hello world";
    }
}
