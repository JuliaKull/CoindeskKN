package com.kull.coindeskkn.utils;

import com.kull.coindeskkn.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.kull.coindeskkn.utils.BpiUtils.checkInput;
import static java.lang.System.in;


@Component
@Slf4j
public class Runner implements CommandLineRunner {

    CurrencyService currencyService = new CurrencyService();


    @Override
    public void run(String... args){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter in which currency you would like to receive latest BTC price and last 180 days min/max:");
            String currency = bufferedReader.readLine();
            checkInput(currency);
            currencyService.receiveActualCurrencyData(currency);
            currencyService.receiveCurrencyHistoricalData(currency);
            bufferedReader.close();
        } catch (Exception e) {
           log.info ("Please, input correct currency code");
        }
    }
}
