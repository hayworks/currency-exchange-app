package com.searchmetrics.currencyexchange.controller;

import com.searchmetrics.currencyexchange.exception.DateValidationException;
import com.searchmetrics.currencyexchange.facade.CurrencyRateFacade;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.model.dto.CurrencyRateHistoryRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyRatesControllerTest {

    @InjectMocks
    private CurrencyRatesController currencyRatesController;

    @Mock
    private CurrencyRateFacade currencyRateFacade;

    @Test
    public void should_get_current_rate_successfully() {

        //given
        when(currencyRateFacade.getCurrentRate()).thenReturn(new CurrencyRate(new BigDecimal(1.1)));

        //when
        CurrencyRate currencyRate = currencyRatesController.getCurrentCurrencyRate();

        //then
        verify(currencyRateFacade).getCurrentRate();
        assertNotNull(currencyRate);
        assertEquals(new BigDecimal(1.1), currencyRate.getRate());

    }

    @Test
    public void should_get_currency_history_successfully() {

        //given
        List<CurrencyRate> currencyRateHistoryList = new ArrayList<CurrencyRate>(){{
           add(new CurrencyRate(new BigDecimal(1.2)));
        }};
        when(currencyRateFacade.getCurrencyRateHistory(any(Date.class), any(Date.class))).thenReturn(currencyRateHistoryList);

        //when
        currencyRateHistoryList = currencyRatesController.getCurrencyRateHistory(new CurrencyRateHistoryRequest(new Date(System.currentTimeMillis()-100000), new Date()));

        //then
        verify(currencyRateFacade).getCurrencyRateHistory(any(Date.class), any(Date.class));
        assertNotNull(currencyRateHistoryList);
        assertEquals(currencyRateHistoryList.size(), 1);

    }

    @Test(expected = DateValidationException.class)
    public void should_throw_exception_on_getting_currency_log_history_when_start_date_is_later_then_end_date() {

        //given
        CurrencyRateHistoryRequest currencyRateHistoryRequest = new CurrencyRateHistoryRequest(new Date(System.currentTimeMillis()+100000), new Date());

        //when
        currencyRatesController.getCurrencyRateHistory(currencyRateHistoryRequest);

    }

}
