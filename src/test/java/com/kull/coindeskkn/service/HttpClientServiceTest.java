package com.kull.coindeskkn.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kull.coindeskkn.model.CurrentPriceResponse;
import com.kull.coindeskkn.model.HistoricalResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HttpClientServiceTest {

    @Spy
    private HttpClient client;

    @BeforeEach
    public void init(){
        client = HttpClient.newBuilder()
                .build();
    }

    @Test
    public void status200ActualPriceResponse() throws Exception {
        String currency = "USD";
        HttpRequest request = HttpRequest.newBuilder(new URI("https://api.coindesk.com/v1/bpi/currentprice/"+currency)).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }

    @Test
    public void send404ActualPriceResponse() throws Exception {
        String currency = "null";
        HttpRequest request = HttpRequest.newBuilder(new URI("https://api.coindesk.com/v1/bpi/currentprice/"+currency)).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
    }

    @Test
    public void status200HistoricalPriceResponse() throws Exception {
        String today = LocalDate.now().toString();
        String halfYearBefore = LocalDate.now().minusDays(180).toString();
        String currency = "USD";
        HttpRequest request = HttpRequest.newBuilder(new URI("https://api.coindesk.com/v1/bpi/historical/close?start="+halfYearBefore+"&end="+today+"&currency=" + currency)).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }

    @Test
    public void send404HistoricalPriceResponse() throws Exception {
        String today = LocalDate.now().toString();
        String halfYearBefore = LocalDate.now().minusDays(180).toString();
        String currency = "null";
        HttpRequest request = HttpRequest.newBuilder(new URI("https://api.coindesk.com/v1/bpi/historical/close?start="+halfYearBefore+"&end="+today+"&currency=" + currency)).build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(404, response.statusCode());
    }

    @Test
    public void objectMapperActualPrice() throws Exception{
        String currency = "USD";
        HttpRequest request = HttpRequest.newBuilder(new URI("https://api.coindesk.com/v1/bpi/currentprice/"+currency)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        CurrentPriceResponse currentPriceResponse = mapper.readValue(response.body(), new TypeReference<CurrentPriceResponse>(){});
        assertDoesNotThrow(() -> mapper.readValue(response.body(), CurrentPriceResponse.class));
        assertThat(currentPriceResponse).isNotNull();
    }

    @Test
    public void objectMapperHistoricalPrice() throws Exception{
        String currency = "USD";
        String today = LocalDate.now().toString();
        String halfYearBefore = LocalDate.now().minusDays(180).toString();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(new URI("https://api.coindesk.com/v1/bpi/historical/close?start="+halfYearBefore+"&end="+today+"&currency=" + currency)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        HistoricalResponse historicalResponse = mapper.readValue(response.body(), new TypeReference<HistoricalResponse>() {});
        assertDoesNotThrow(() -> mapper.readValue(response.body(), HistoricalResponse.class));
        assertThat(historicalResponse).isNotNull();


    }


}