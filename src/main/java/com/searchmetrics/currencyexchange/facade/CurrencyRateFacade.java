package com.searchmetrics.currencyexchange.facade;

import com.searchmetrics.currencyexchange.dao.CurrencyRateDao;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.repository.CurrentCurrencyRateRepository;
import com.searchmetrics.currencyexchange.service.CurrencyDataService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CurrencyRateFacade {

    private CurrentCurrencyRateRepository currentCurrencyRateRepository;

    private CurrencyRateDao currencyRateDao;

    private CurrencyDataService currencyDataService;

    public CurrencyRate getCurrentRate() {
        return currentCurrencyRateRepository.getCurrencyRate();
    }

    public List<CurrencyRate> getCurrencyRateHistory(Date startDate, Date endDate) {
        return currencyRateDao.getAllBetweenDates(startDate, endDate);
    }

    public void updateCurrentCurrencyRate() {
        currencyRateDao.insertNewHistoryRateItems(currentCurrencyRateRepository.getCurrencyRate());

        BigDecimal rate = currencyDataService.getLatestExchangeRate();

        this.currentCurrencyRateRepository.updateCurrentRate(rate);
    }
}
