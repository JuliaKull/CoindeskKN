package com.kull.coindeskkn.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kull.coindeskkn.model.CurrentPriceResponse;
import com.kull.coindeskkn.model.HistoricalResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
@Service
public class HttpClientService {

    private final static String BPI_URL="https://api.coindesk.com/v1/bpi/currentprice/";

    private final static String HISTORICAL_BPI_URL ="https://api.coindesk.com/v1/bpi/historical/close";

    private final static String TODAY = LocalDate.now().toString();

    private final static String HALF_YEAR_BEFORE = LocalDate.now().minusDays(180).toString();

    public CurrentPriceResponse getActualPriceResponse(String currency) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(BPI_URL+currency))
                .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            CurrentPriceResponse currentPriceResponse = mapper.readValue(response.body(), new TypeReference<CurrentPriceResponse>() {
            });
            return currentPriceResponse;}

    public HistoricalResponse getHistoricalPriceResponse(String currency) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(HISTORICAL_BPI_URL+ "?start="+HALF_YEAR_BEFORE+"&end="+TODAY+"&currency=" + currency))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        HistoricalResponse historicalResponse = mapper.readValue(response.body(), new TypeReference<HistoricalResponse>() {
        });
        return historicalResponse;}
    }
