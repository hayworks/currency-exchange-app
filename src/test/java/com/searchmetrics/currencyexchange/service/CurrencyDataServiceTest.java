package com.searchmetrics.currencyexchange.service;

import com.searchmetrics.currencyexchange.model.CurrencyServiceResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyDataServiceTest {

    private CurrencyDataService currencyDataService;

    @Mock
    private RestTemplate restTemplate;

    @Before
    public void before() {
        currencyDataService = new CurrencyDataService("http://www.asd.com");
        currencyDataService.setRestTemplate(restTemplate);
    }

    @Test
    public void should_call_service_successfully() throws URISyntaxException {

        //given
        when(restTemplate.getForObject(any(URI.class), any())).thenReturn(new CurrencyServiceResponse(new BigDecimal(1.2)));

        //when
        BigDecimal rate = currencyDataService.getLatestExchangeRate();

        //then
        assertNotNull(rate);
        verify(restTemplate).getForObject(any(URI.class), any());

    }

}
