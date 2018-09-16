package com.searchmetrics.currencyexchange.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class CurrencyRate {

    private BigDecimal rate;
    private Date createDate;

    public CurrencyRate(BigDecimal rate) {
        this.rate = rate;
        this.createDate = new Date();
    }

}
