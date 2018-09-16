package com.searchmetrics.currencyexchange.facade;

import com.searchmetrics.currencyexchange.dao.CurrencyRateDao;
import com.searchmetrics.currencyexchange.exception.CurrencyRateUnavailableException;
import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.repository.CurrentCurrencyRateRepository;
import com.searchmetrics.currencyexchange.service.CurrencyDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyRateFacadeTest {

    @InjectMocks
    private CurrencyRateFacade currencyRateFacade;

    @Mock
    private CurrentCurrencyRateRepository currentCurrencyRateRepository;

    @Mock
    private CurrencyRateDao currencyRateDao;

    @Mock
    private CurrencyDataService currencyDataService;

    @Test
    public void should_get_current_rate_successfully() {

        //given
        when(currentCurrencyRateRepository.getCurrencyRate())
                .thenReturn(new CurrencyRate(new BigDecimal(1.2)));

        //when
        CurrencyRate currencyRate = currencyRateFacade.getCurrentRate();

        //then
        verify(currentCurrencyRateRepository).getCurrencyRate();
        assertNotNull(currencyRate);
        assertEquals(currencyRate.getRate(), new BigDecimal(1.2));

    }

    @Test(expected = CurrencyRateUnavailableException.class)
    public void should_throw_exception_when_current_rate_could_not_get_fetched() {

        //given
        when(currentCurrencyRateRepository.getCurrencyRate()).thenThrow(CurrencyRateUnavailableException.class);

        //when
        currencyRateFacade.getCurrentRate();

        //then

    }

    @Test
    public void should_get_rate_history_successfully() {

        //given
        when(currencyRateDao.getAllBetweenDates(any(Date.class), any(Date.class))).thenReturn(new ArrayList<CurrencyRate>(){{
            add(new CurrencyRate(new BigDecimal(1.2)));
        }});

        //when
        List<CurrencyRate> currencyRateHistoryList = currencyRateFacade.getCurrencyRateHistory(new Date(), new Date());

        //then
        verify(currencyRateDao).getAllBetweenDates(any(Date.class), any(Date.class));
        assertNotNull(currencyRateHistoryList);

    }

    @Test
    public void should_update_current_rate_successfully() throws URISyntaxException {

        //given
        doNothing().when(currencyRateDao).insertNewHistoryRateItems(any(CurrencyRate.class));
        when(currencyDataService.getLatestExchangeRate()).thenReturn(new BigDecimal(1.2));
        doNothing().when(currentCurrencyRateRepository).updateCurrentRate(any(BigDecimal.class));
        when(currentCurrencyRateRepository.getCurrencyRate()).thenReturn(new CurrencyRate(new BigDecimal(1.2)));

        //when
        currencyRateFacade.updateCurrentCurrencyRate();

        //then
        verify(currencyDataService).getLatestExchangeRate();
        verify(currencyRateDao).insertNewHistoryRateItems(any(CurrencyRate.class));
        verify(currentCurrencyRateRepository).updateCurrentRate(any(BigDecimal.class));

    }

    @Test
    public void should_put_null_when_currency_service_is_unreachable() throws URISyntaxException {

        //given
        doNothing().when(currencyRateDao).insertNewHistoryRateItems(any(CurrencyRate.class));
        when(currencyDataService.getLatestExchangeRate()).thenThrow(new RestClientException("dummy text"));
        when(currentCurrencyRateRepository.getCurrencyRate()).thenReturn(new CurrencyRate(new BigDecimal(1.2)));

        //when
        currencyRateFacade.updateCurrentCurrencyRate();

        //then
        verify(currencyDataService).getLatestExchangeRate();
        verify(currencyRateDao).insertNewHistoryRateItems(any(CurrencyRate.class));
        verify(currentCurrencyRateRepository).updateCurrentRate(null);

    }

}
