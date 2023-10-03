package com.example.spark.controller;

import com.example.spark.service.SparkService;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/spark")
public class SparkController {

    @Autowired
    private SparkService sparkService;

    @GetMapping
    public Dataset<Row> processData() {
        SparkSession sparkSession = sparkService.getSparkSession();

        // 读取CSV文件
        Dataset<Row> dataset = sparkSession.read().option("header", true).csv("E:\\Middleware\\bigdata\\spark\\src\\main\\resources\\data.csv");

        // 执行数据处理和分析操作
//        dataset = dataset.select("column1", "column2")
//                .filter("column1 > 10")
//                .groupBy("column2")
//                .count();
        dataset = dataset.select("column1\tcolumn2");

        return dataset;
    }
}