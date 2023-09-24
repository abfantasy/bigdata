package com.example.doris.controller;

import com.example.doris.entity.DorisUser;
import com.example.doris.service.DorisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/doris")
public class DorisController {

    @Autowired
    DorisService dorisService;

    @GetMapping("/list")
    public List<DorisUser> listDoris() {
        return dorisService.listDoris();
    }

    @GetMapping("/query")
    public DorisUser getDoris(int id) {
        return dorisService.quryDoris(id);
    }

    @GetMapping("/update")
    public void updateDoris(int id, String name) {
        dorisService.updateDoris(id, name);
    }
}