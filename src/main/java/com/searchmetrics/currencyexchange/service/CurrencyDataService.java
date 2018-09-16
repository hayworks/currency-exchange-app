package com.searchmetrics.currencyexchange.service;

import com.searchmetrics.currencyexchange.model.CurrencyServiceResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class CurrencyDataService {

    @Setter
    private RestTemplate restTemplate;

    private String url;

    public CurrencyDataService(@Value("${currency.service.url}") String url) {
        restTemplate = new RestTemplate();
        this.url = url;
    }

    public BigDecimal getLatestExchangeRate() throws URISyntaxException {
        return restTemplate.getForObject(new URI(url), CurrencyServiceResponse.class).getRate();
    }
}
