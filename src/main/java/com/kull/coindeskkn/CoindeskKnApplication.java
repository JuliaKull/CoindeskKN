package com.kull.coindeskkn;

import com.kull.coindeskkn.service.CurrencyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.kull.coindeskkn.utils.BpiUtils.checkInput;

@SpringBootApplication
public class CoindeskKnApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoindeskKnApplication.class, args);
        }}
