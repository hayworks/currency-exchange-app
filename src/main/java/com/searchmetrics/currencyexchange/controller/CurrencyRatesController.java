package com.searchmetrics.currencyexchange.controller;

import com.searchmetrics.currencyexchange.exception.DateValidationException;
import com.searchmetrics.currencyexchange.facade.CurrencyRateFacade;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.model.dto.CurrencyRateHistoryRequest;

import java.util.List;

public class CurrencyRatesController {

    private CurrencyRateFacade currencyRateFacade;

    public CurrencyRate getCurrentCurrencyRate() {

        return currencyRateFacade.getCurrentRate();
    }

    public List<CurrencyRate> getCurrencyRateHistory(CurrencyRateHistoryRequest currencyRateHistoryRequest) {

        if(!currencyRateHistoryRequest.isValid())
            throw new DateValidationException("start date can not be later then end date!");

        return currencyRateFacade.getCurrencyRateHistory(currencyRateHistoryRequest.getStartDate(), currencyRateHistoryRequest.getEndDate());
    }

}
