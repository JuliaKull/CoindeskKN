package com.kull.coindeskkn.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BpiUtils {

    public static void checkInput(String currency) {
        try {
            if (currency.isBlank()) {
                log.info("Currency can`t be empty");
            } else if (currency == null) {
                log.info("Currency can`t be null");
            } else if (!currency.matches("[a-zA-Z]{3}")) {
                log.info("Incorrect currency code");
            }
        } catch (Exception e) {
            log.info("Currency code not input");
        }
    }
}




