package com.searchmetrics.currencyexchange.dao;

import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CurrencyRateDao {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    public List<CurrencyRate> getAllBetweenDates(Date startDate, Date endDate) {
        return currencyRateRepository.findBetweenStartAndEndDate(startDate, endDate);
    }

    public void insertNewHistoryRateItems(CurrencyRate rate) {
        currencyRateRepository.save(rate);
    }
}
