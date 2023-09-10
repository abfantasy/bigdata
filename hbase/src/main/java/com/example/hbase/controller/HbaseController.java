package com.example.hbase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

@Controller
@RequestMapping("/hbase")
public class HbaseController {
    @Resource
    private com.example.hbase.config.HBaseClient client;

    @GetMapping("/test")
    @ResponseBody
    public String test() throws IOException {
        System.err.println("tableExists = " + client.tableExists("test"));
        return "success";
    }

    @GetMapping("/add")
    @ResponseBody
    public String add(String rowNum) throws IOException {
        System.err.println("tableExists = " + client.tableExists("test"));
        client.insertOrUpdate("test", rowNum, "cf", "address", "weth");
        return "success";
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete(String rowNum) throws IOException {
        client.deleteRow("test", rowNum);
        return "success";
    }

    @GetMapping("/search")
    @ResponseBody
    public String search(String rowNum) throws IOException {
        String test = client.selectOneRow("test", rowNum);
        System.err.println(test);
        return "success";
    }
}
