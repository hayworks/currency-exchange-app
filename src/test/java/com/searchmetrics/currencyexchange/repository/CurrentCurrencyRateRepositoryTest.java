package com.searchmetrics.currencyexchange.repository;

import com.searchmetrics.currencyexchange.exception.CurrencyRateUnavailableException;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CurrentCurrencyRateRepositoryTest {

    private CurrentCurrencyRateRepository currentCurrencyRateRepository = new CurrentCurrencyRateRepository();

    @Test
    public void should_get_current_rate_successfully() {

        //given
        currentCurrencyRateRepository.updateCurrentRate(new BigDecimal(1.2));

        //when
        CurrencyRate currencyRate = currentCurrencyRateRepository.getCurrencyRate();

        //then
        assertNotNull(currencyRate);
        assertEquals(currencyRate.getRate(), new BigDecimal(1.2));

    }

    @Test(expected = CurrencyRateUnavailableException.class)
    public void should_throw_exception_when_current_rate_is_null() {

        //given

        //when
        currentCurrencyRateRepository.getCurrencyRate();

        //then

    }

}
