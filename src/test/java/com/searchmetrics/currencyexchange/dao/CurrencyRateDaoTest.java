package com.searchmetrics.currencyexchange.dao;

import com.searchmetrics.currencyexchange.model.CurrencyRate;
import com.searchmetrics.currencyexchange.repository.CurrencyRateRepository;
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
public class CurrencyRateDaoTest {

    @InjectMocks
    private CurrencyRateDao currencyRateDao;

    @Mock
    private CurrencyRateRepository currencyRateHistoryRequest;

    @Test
    public void should_get_currency_history_items_successfully() {

        //given
        when(currencyRateHistoryRequest.findBetweenStartAndEndDate(any(Date.class), any(Date.class))).thenReturn(new ArrayList<CurrencyRate>(){{
            add(new CurrencyRate(new BigDecimal(1.2)));
        }});

        //when
        List<CurrencyRate> currencyRateList = currencyRateDao.getAllBetweenDates(new Date(System.currentTimeMillis()-100000), new Date(System.currentTimeMillis() + 100000));

        //then
        assertNotNull(currencyRateList);
        assertEquals(currencyRateList.size() , 1);
    }

    @Test
    public void should_save_currency_rate_successfully() {

        //given
        when(currencyRateHistoryRequest.save(any(CurrencyRate.class))).thenReturn(any(CurrencyRate.class));

        //when
        currencyRateDao.insertNewHistoryRateItems(new CurrencyRate(new BigDecimal(1.2)));

        //then
        verify(currencyRateHistoryRequest).save(any(CurrencyRate.class));
    }

}
