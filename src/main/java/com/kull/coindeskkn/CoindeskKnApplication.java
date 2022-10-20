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
        CurrencyService currencyService = new CurrencyService();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter in which currency you would like to receive latest BTC price and last 180 days min/max:");
            String currency = in.readLine();
            checkInput(currency);
            currencyService.receiveActualCurrencyData(currency);
            currencyService.receiveCurrencyHistoricalData(currency);
            in.close();

            } catch (IOException e) {
                throw new RuntimeException("Currency code not found");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }}
