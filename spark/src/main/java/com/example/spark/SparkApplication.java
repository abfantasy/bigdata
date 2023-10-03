package com.example.spark;

import com.example.spark.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class SparkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkApplication.class, args);
    }


    @Autowired
    private SparkService sparkService;

    @PostConstruct
    public void initSpark() {
        sparkService.initSparkSession();
    }
}
