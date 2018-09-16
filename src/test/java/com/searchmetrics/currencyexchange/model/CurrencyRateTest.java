package com.searchmetrics.currencyexchange.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CurrencyRateTest {

    @Test
    public void should_new_object_have_a_creation_date_and_rate_val() {
        //given

        //when
        CurrencyRate currencyRate = new CurrencyRate(new BigDecimal(1.2));

        //then
        assertNotNull(currencyRate.getCreateDate());
        assertEquals(currencyRate.getRate(), new BigDecimal(1.2));
    }

}
