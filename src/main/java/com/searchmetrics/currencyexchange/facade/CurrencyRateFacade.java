package com.searchmetrics.currencyexchange.facade;

import com.searchmetrics.currencyexchange.dao.CurrencyRateDao;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.repository.CurrentCurrencyRateRepository;

import java.util.Date;
import java.util.List;

public class CurrencyRateFacade {

    private CurrentCurrencyRateRepository currentCurrencyRateRepository;

    private CurrencyRateDao currencyRateDao;

    public CurrencyRate getCurrentRate() {
        return currentCurrencyRateRepository.getCurrencyRate();
    }

    public List<CurrencyRate> getCurrencyRateHistory(Date startDate, Date endDate) {
        return currencyRateDao.getAllBetweenDates(startDate, endDate);
    }
}
