package com.searchmetrics.currencyexchange.dao;

import com.searchmetrics.currencyexchange.model.CurrencyRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CurrencyRateDao.class)
public class CurrencyRateDaoTest {

    private CurrencyRateDao currencyRateDao;

    @Test
    public void should_get_currency_history_items_successfully() {

        //given
        currencyRateDao.insertNewHistoryRateItems(new CurrencyRate(new BigDecimal(1.2)));
        currencyRateDao.insertNewHistoryRateItems(new CurrencyRate(new BigDecimal(1.4)));

        //when
        List<CurrencyRate> currencyRateList = currencyRateDao.getAllBetweenDates(new Date(System.currentTimeMillis()-100000), new Date(System.currentTimeMillis() + 100000));

        //then
        assertNotNull(currencyRateList);
        assertEquals(currencyRateList.size() , 2);
    }

}
