package com.searchmetrics.currencyexchange.repository;

import com.searchmetrics.currencyexchange.exception.CurrencyRateUnavailableException;
import com.searchmetrics.currencyexchange.model.CurrencyRate;

public class CurrentCurrencyRateRepository {

    private CurrencyRate currentRate;

    public CurrencyRate getCurrencyRate() {

        if(currentRate == null)
            throw new CurrencyRateUnavailableException("Currency rate is not available now. Please try again some time later!");

        return currentRate;
    }

}
