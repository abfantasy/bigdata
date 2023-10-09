package com.example.spark.statusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/code")
public class OtherController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/step1")
    @ResponseBody
    public String show499() throws InterruptedException {
        System.err.println("step1 - 1111");
        Thread.sleep(500000);
        System.err.println("step2 - 2222");
        return "{}";
    }

    @GetMapping("/step2")
    @ResponseBody
    public String show504() throws InterruptedException {
        System.err.println("step2 - 1111");
        Thread.sleep(50000);
        System.err.println("step2 - 2222");
        return "{}";
    }

}
