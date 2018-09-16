package com.searchmetrics.currencyexchange.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyRateHistoryRequest {

    private Date startDate;
    private Date endDate;

    public boolean isValid() {
        return startDate.compareTo(endDate) == -1;
    }
}
