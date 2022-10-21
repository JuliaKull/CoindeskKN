package com.kull.coindeskkn.utils;

import com.kull.coindeskkn.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.kull.coindeskkn.utils.BpiUtils.checkInput;

@Component
public class Runner implements CommandLineRunner {

    CurrencyService currencyService = new CurrencyService();


    @Override
    public void run(String... args) throws Exception {
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
    }
}
