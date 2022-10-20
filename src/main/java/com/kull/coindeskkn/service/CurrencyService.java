package com.kull.coindeskkn.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kull.coindeskkn.model.CurrentPriceResponse;
import com.kull.coindeskkn.model.HistoricalResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Service
public class CurrencyService {
    private HttpClientService apiService = new HttpClientService();

    private ObjectMapper mapper = new ObjectMapper();


    public  void receiveActualCurrencyData(String currency) throws IOException, InterruptedException {
        CurrentPriceResponse response = apiService.getActualPriceResponse(currency);
        String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getBpi().get(currency.toUpperCase()));
        String timeResponse = mapper.writeValueAsString(response.getTime());
        System.out.println(timeResponse);
        System.out.println(prettyJson);
    }

    public void receiveCurrencyHistoricalData(String currency) throws IOException, InterruptedException {
        
        HistoricalResponse response = apiService.getHistoricalPriceResponse(currency);

        System.out.println("The highest Bitcoin rate in the last 180 days, in the requested currency was on "
                + getMaximumRate(response).getKey()
                + " price was "
                + getMaximumRate(response).getValue());

        System.out.println("The lowest Bitcoin rate in the last 180 days, in the requested currency was on "
                + getMinimumRate(response).getKey()
                + " price was "
                + getMinimumRate(response).getValue());

    }
    public Map.Entry<String, BigDecimal> getMaximumRate(HistoricalResponse response) {
        return Collections.max(response.getBpi().entrySet(), Map.Entry.comparingByValue());
    }

    public  Map.Entry<String, BigDecimal> getMinimumRate(HistoricalResponse response) {
        return Collections.min(response.getBpi().entrySet(), Map.Entry.comparingByValue());
    }


}
