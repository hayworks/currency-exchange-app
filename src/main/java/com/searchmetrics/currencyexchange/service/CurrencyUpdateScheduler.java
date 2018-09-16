package com.searchmetrics.currencyexchange.service;

import com.searchmetrics.currencyexchange.facade.CurrencyRateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CurrencyUpdateScheduler {

    @Autowired
    private CurrencyRateFacade currencyRateFacade;

    @Scheduled(fixedRateString = "${currency.service.interval}")
    public void updateCurrentExchangeRate() {
        this.currencyRateFacade.updateCurrentCurrencyRate();
    }

}
