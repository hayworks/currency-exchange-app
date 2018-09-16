package com.searchmetrics.currencyexchange.facade;

import com.searchmetrics.currencyexchange.dao.CurrencyRateDao;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.repository.CurrentCurrencyRateRepository;
import com.searchmetrics.currencyexchange.service.CurrencyDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class CurrencyRateFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyRateFacade.class);

    @Autowired
    private CurrentCurrencyRateRepository currentCurrencyRateRepository;

    @Autowired
    private CurrencyRateDao currencyRateDao;

    @Autowired
    private CurrencyDataService currencyDataService;

    public CurrencyRate getCurrentRate() {
        return currentCurrencyRateRepository.getCurrencyRate();
    }

    public List<CurrencyRate> getCurrencyRateHistory(Date startDate, Date endDate) {
        return currencyRateDao.getAllBetweenDates(startDate, endDate);
    }

    public void updateCurrentCurrencyRate() {
        try {
            currencyRateDao.insertNewHistoryRateItems(currentCurrencyRateRepository.getCurrencyRate());
        } catch (Exception e) {
            LOGGER.warn("Current rate is null");
        }


        BigDecimal rate = null;
        try {
            rate = currencyDataService.getLatestExchangeRate();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        this.currentCurrencyRateRepository.updateCurrentRate(rate);
    }
}
