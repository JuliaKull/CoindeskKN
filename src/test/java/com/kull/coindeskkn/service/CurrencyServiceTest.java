package com.kull.coindeskkn.service;

import com.kull.coindeskkn.model.HistoricalResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {
    @InjectMocks
    private CurrencyService currencyService;

    @Test
    public void shouldGetMinimumRate(){
        Map<String, BigDecimal> bpi = new HashMap<>();
        bpi.put("2022-10-21", BigDecimal.valueOf(10));
        bpi.put("2022-10-22", BigDecimal.valueOf(20));

        Map.Entry<String, BigDecimal> minRate = currencyService.getMinimumRate(new HistoricalResponse(bpi));
        assertEquals("2022-10-21", minRate.getKey());
        assertEquals(BigDecimal.valueOf(10), minRate.getValue());

    }

    @Test
    public void shouldGetMaximumRate(){
        Map<String, BigDecimal> bpi = new HashMap<>();
        bpi.put("2022-10-21", BigDecimal.valueOf(10));
        bpi.put("2022-10-22", BigDecimal.valueOf(20));

        Map.Entry<String, BigDecimal> minRate = currencyService.getMaximumRate(new HistoricalResponse(bpi));
        assertEquals("2022-10-22", minRate.getKey());
        assertEquals(BigDecimal.valueOf(20), minRate.getValue());

    }

}