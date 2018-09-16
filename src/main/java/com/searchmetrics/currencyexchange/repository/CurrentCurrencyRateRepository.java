package com.searchmetrics.currencyexchange.repository;

import com.searchmetrics.currencyexchange.exception.CurrencyRateUnavailableException;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CurrentCurrencyRateRepository {

    private AtomicReference<CurrencyRate> currentRate = new AtomicReference<>();

    public CurrencyRate getCurrencyRate() {

        if(currentRate.get() == null)
            throw new CurrencyRateUnavailableException("Currency rate is not available now. Please try again some time later!");

        return currentRate.get();
    }

    public void updateCurrentRate(BigDecimal bigDecimal) {
        currentRate.set(new CurrencyRate(bigDecimal));
    }
}
