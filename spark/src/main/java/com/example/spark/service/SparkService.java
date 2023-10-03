package com.example.spark.service;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;
import org.springframework.stereotype.Service;

@Service
public class SparkService {

    private SparkSession sparkSession;

    public void initSparkSession() {
        SparkConf sparkConf = new SparkConf()
                .setAppName("Spark Spring Boot Demo")
                .setMaster("local[*]");

        sparkSession = SparkSession.builder()
                .config(sparkConf)
                .getOrCreate();
    }

    public SparkSession getSparkSession() {
        return sparkSession;
    }

    public void closeSparkSession() {
        sparkSession.close();
    }
}