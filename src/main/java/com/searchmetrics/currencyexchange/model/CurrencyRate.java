package com.searchmetrics.currencyexchange.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CurrencyRate {

    private BigDecimal rate;
    private Date createDate;

    public CurrencyRate(BigDecimal rate) {
        this.rate = rate;
        this.createDate = new Date();
    }

    public BigDecimal getRate() {
        return rate;
    }
}
