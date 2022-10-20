package com.kull.coindeskkn.utils;

public class BpiUtils {

    public static void checkInput(String currency){
        if(!currency.matches("[a-zA-Z]{3}")){
            throw new RuntimeException("Incorrect input of currency code");
        }
    }
}
