package com.searchmetrics.currencyexchange.controller;

import com.searchmetrics.currencyexchange.exception.DateValidationException;
import com.searchmetrics.currencyexchange.facade.CurrencyRateFacade;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.model.dto.CurrencyRateHistoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rate")
public class CurrencyRatesController {

    @Autowired
    private CurrencyRateFacade currencyRateFacade;

    @GetMapping("/current")
    public CurrencyRate getCurrentCurrencyRate() {

        return currencyRateFacade.getCurrentRate();
    }

    @PostMapping("/old")
    public List<CurrencyRate> getCurrencyRateHistory(@RequestBody CurrencyRateHistoryRequest currencyRateHistoryRequest) {

        if(!currencyRateHistoryRequest.isValid())
            throw new DateValidationException("start date can not be later then end date!");

        return currencyRateFacade.getCurrencyRateHistory(currencyRateHistoryRequest.getStartDate(), currencyRateHistoryRequest.getEndDate());
    }

}
